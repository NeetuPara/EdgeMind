package com.edgemind.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edgemind.app.data.TaskIds
import com.edgemind.app.data.getHfToken
import com.edgemind.app.data.saveHfToken
import com.edgemind.app.ui.components.AuroraBackground
import com.edgemind.app.ui.theme.DeepOcean
import com.edgemind.app.ui.theme.Teal
import com.edgemind.app.ui.theme.TealDark
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onTaskSelected: (String) -> Unit) {
    var showSettings by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var hfToken by remember { mutableStateOf("") }

    // Load saved token on first composition
    LaunchedEffect(Unit) {
        hfToken = context.getHfToken()
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        AuroraBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(Modifier.height(32.dp))

            // Header row with settings
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("EdgeMind", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(4.dp))
                    Text("Choose a task to get started", color = Color.White.copy(alpha = 0.5f), fontSize = 14.sp)
                }
                IconButton(onClick = { showSettings = true }) {
                    Icon(Icons.Rounded.Settings, "Settings", tint = Color.White.copy(alpha = 0.6f))
                }
            }

            Spacer(Modifier.height(40.dp))

            // Task cards
            TaskCard(
                icon = Icons.Rounded.Chat,
                title = "AI Chat",
                description = "Have text conversations with on-device AI models. Ask questions, get summaries, brainstorm ideas — all privately on your device.",
                gradient = listOf(Teal, TealDark),
                badge = "TEXT",
                onClick = { onTaskSelected(TaskIds.LLM_CHAT) },
            )

            Spacer(Modifier.height(20.dp))

            TaskCard(
                icon = Icons.Rounded.Image,
                title = "Ask Image",
                description = "Upload images and ask AI to describe, analyze, or answer questions about them. Powered by vision-language models.",
                gradient = listOf(DeepOcean, Color(0xFF0A1825)),
                badge = "TEXT + VISION",
                onClick = { onTaskSelected(TaskIds.LLM_ASK_IMAGE) },
            )

            Spacer(Modifier.height(20.dp))

            TaskCard(
                icon = Icons.Rounded.Description,
                title = "Ask PDF",
                description = "Upload a PDF document and ask questions about its text content. Ideal for summarizing and querying long text.",
                gradient = listOf(Color(0xFF3F51B5), Color(0xFF1A237E)), // Indigo gradient
                badge = "TEXT",
                onClick = { onTaskSelected(TaskIds.LLM_ASK_PDF) },
            )

            Spacer(Modifier.weight(1f))

            // Footer
            Text(
                "All models run 100% offline on your device",
                color = Color.White.copy(alpha = 0.3f),
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(24.dp))
        }
    }

    // Settings dialog
    if (showSettings) {
        AlertDialog(
            onDismissRequest = { showSettings = false },
            containerColor = Color(0xFF151B20),
            title = { Text("Settings", color = Color.White) },
            text = {
                Column {
                    Text(
                        "HuggingFace Access Token",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Required for downloading gated models (e.g. Gemma). Get your token from huggingface.co/settings/tokens",
                        color = Color.White.copy(alpha = 0.4f),
                        fontSize = 12.sp,
                    )
                    Spacer(Modifier.height(12.dp))
                    OutlinedTextField(
                        value = hfToken,
                        onValueChange = { hfToken = it },
                        placeholder = { Text("hf_...", color = Color.White.copy(alpha = 0.2f)) },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Teal,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.15f),
                            cursorColor = Teal,
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    scope.launch {
                        context.saveHfToken(hfToken)
                        showSettings = false
                    }
                }) {
                    Text("Save", color = Teal)
                }
            },
            dismissButton = {
                TextButton(onClick = { showSettings = false }) {
                    Text("Cancel", color = Color.White.copy(alpha = 0.5f))
                }
            },
        )
    }
}

@Composable
private fun TaskCard(
    icon: ImageVector,
    title: String,
    description: String,
    gradient: List<Color>,
    badge: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(gradient),
                    RoundedCornerShape(24.dp),
                )
                .padding(24.dp),
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
                    Spacer(Modifier.width(12.dp))
                    Text(title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.weight(1f))
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.White.copy(alpha = 0.15f),
                    ) {
                        Text(
                            badge,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    description,
                    color = Color.White.copy(alpha = 0.75f),
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                )

                Spacer(Modifier.height(16.dp))

                Text("Tap to browse models →", color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp)
            }
        }
    }
}
