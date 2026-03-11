package com.edgemind.app.ui.navigation


import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edgemind.app.data.TaskIds
import com.edgemind.app.ui.screens.*
import com.edgemind.app.viewmodel.ChatViewModel
import com.edgemind.app.viewmodel.ModelManagerViewModel

object Routes {
    const val SPLASH = "splash"
    const val PIN = "pin"
    const val HOME = "home"
    const val MODEL_LIST = "model_list/{taskId}/{taskLabel}"
    const val CHAT = "chat/{modelName}/{taskId}"

    fun modelList(taskId: String, taskLabel: String) = "model_list/$taskId/$taskLabel"
    fun chat(modelName: String, taskId: String) = "chat/$modelName/$taskId"
}

@Composable
fun EdgeMindNavHost() {
    val navController = rememberNavController()
    val modelManagerVM: ModelManagerViewModel = hiltViewModel()
    val modelManagerState by modelManagerVM.uiState.collectAsState()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {

        composable(Routes.SPLASH) {
            SplashScreen(onFinished = {
                navController.navigate(Routes.PIN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            })
        }

        composable(Routes.PIN) {
            PinScreen(onAuthenticated = {
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.PIN) { inclusive = true }
                }
            })
        }

        composable(Routes.HOME) {
            HomeScreen(onTaskSelected = { taskId ->
                val label = when (taskId) {
                    TaskIds.LLM_CHAT -> "AI Chat"
                    TaskIds.LLM_ASK_IMAGE -> "Ask Image"
                    TaskIds.LLM_ASK_PDF -> "Ask PDF"
                    else -> "Models"
                }
                navController.navigate(Routes.modelList(taskId, label))
            })
        }

        composable(Routes.MODEL_LIST) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            val taskLabel = backStackEntry.arguments?.getString("taskLabel") ?: ""

            ModelListScreen(
                taskId = taskId,
                taskLabel = taskLabel,
                state = modelManagerState,
                onBack = { navController.popBackStack() },
                onDownload = { model -> modelManagerVM.downloadModel(model) },
                onCancel = { model -> modelManagerVM.cancelDownload(model) },
                onChat = { model ->
                    navController.navigate(Routes.chat(model.name, taskId))
                },
                onDelete = { model -> modelManagerVM.deleteModel(model) },
            )
        }

        composable(Routes.CHAT) { backStackEntry ->
            val modelName = backStackEntry.arguments?.getString("modelName") ?: ""
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            val chatVM: ChatViewModel = hiltViewModel()
            val chatState by chatVM.uiState.collectAsState()

            // Current model name (may change when user switches)
            var activeModelName by remember { mutableStateOf(modelName) }

            // All downloaded models compatible with this task
            val availableModels = remember(modelManagerState.allModels, modelManagerState.downloadStatuses, taskId) {
                modelManagerState.allModels.filter { model ->
                    model.taskTypes.contains(taskId) &&
                    modelManagerState.downloadStatuses[model.name]?.status == com.edgemind.app.data.DownloadStatusType.SUCCEEDED
                }
            }

            // Initialize model on first composition
            LaunchedEffect(activeModelName, taskId) {
                val model = modelManagerState.allModels.find { it.name == activeModelName } ?: return@LaunchedEffect
                chatVM.setActiveModel(model, taskId)
                chatVM.setModelLoading(true)
                modelManagerVM.initializeModel(
                    model = model,
                    taskId = taskId,
                    onDone = { chatVM.setModelLoading(false) },
                    onError = { error ->
                        chatVM.setModelLoading(false)
                        chatVM.setError(error)
                    },
                )
            }

            ChatScreen(
                modelName = activeModelName,
                taskId = taskId,
                state = chatState,
                availableModels = availableModels,
                onBack = {
                    val model = modelManagerState.allModels.find { it.name == activeModelName }
                    model?.let { modelManagerVM.cleanupModel(it) }
                    navController.popBackStack()
                },
                onSendMessage = { text, attachments ->
                    chatVM.sendMessage(text, attachments)
                },
                onNewChat = { chatVM.startNewChat() },
                onLoadSession = { chatVM.loadSession(it) },
                onDeleteSession = { chatVM.deleteSession(it) },
                onStopGeneration = { chatVM.stopGeneration() },
                onSwitchModel = { newModel ->
                    val current = modelManagerState.allModels.find { it.name == activeModelName }
                    current?.let { modelManagerVM.cleanupModel(it) }
                    activeModelName = newModel.name
                    chatVM.setActiveModel(newModel, taskId)
                    chatVM.startNewChat()
                    chatVM.setModelLoading(true)
                    modelManagerVM.initializeModel(
                        model = newModel,
                        taskId = taskId,
                        onDone = { chatVM.setModelLoading(false) },
                        onError = { error ->
                            chatVM.setModelLoading(false)
                            chatVM.setError(error)
                        },
                    )
                },
            )
        }
    }
}
