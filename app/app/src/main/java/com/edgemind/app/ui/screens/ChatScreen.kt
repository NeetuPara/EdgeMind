package com.edgemind.app.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.edgemind.app.data.Model
import com.edgemind.app.data.TaskIds
import com.edgemind.app.ui.components.AuroraBackground
import com.edgemind.app.ui.theme.*
import com.edgemind.app.viewmodel.Attachment
import com.edgemind.app.viewmodel.AttachmentType
import com.edgemind.app.viewmodel.ChatUiState
import com.edgemind.app.viewmodel.UiChatMessage
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    modelName: String,
    taskId: String,
    state: ChatUiState,
    availableModels: List<Model>,          // downloaded models compatible with this task
    onBack: () -> Unit,
    onSendMessage: (text: String, attachments: List<Attachment>) -> Unit,
    onNewChat: () -> Unit,
    onLoadSession: (String) -> Unit,
    onDeleteSession: (String) -> Unit,
    onStopGeneration: () -> Unit,
    onSwitchModel: (Model) -> Unit,        // called when user picks a different model
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var inputText by remember { mutableStateOf("") }
    val attachments = remember { mutableStateListOf<Attachment>() }
    val context = LocalContext.current
    val isVisionTask = taskId == TaskIds.LLM_ASK_IMAGE
    var showAttachMenu by remember { mutableStateOf(false) }

    // Model switcher bottom sheet
    val modelSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showModelSheet by remember { mutableStateOf(false) }

    // Camera photo URI
    var cameraUri by remember { mutableStateOf<android.net.Uri?>(null) }

    // Image picker
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            val tmpFile = File(context.cacheDir, "picked_image_${System.currentTimeMillis()}.jpg")
            inputStream?.use { input -> tmpFile.outputStream().use { output -> input.copyTo(output) } }
            attachments.add(Attachment(path = tmpFile.absolutePath, name = "Photo", type = AttachmentType.IMAGE))
        }
    }

    // Camera launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && cameraUri != null) {
            val tmpFile = File(context.cacheDir, "camera_${System.currentTimeMillis()}.jpg")
            context.contentResolver.openInputStream(cameraUri!!)?.use { input ->
                tmpFile.outputStream().use { output -> input.copyTo(output) }
            }
            attachments.add(Attachment(path = tmpFile.absolutePath, name = "Camera Photo", type = AttachmentType.IMAGE))
        }
    }

    // Camera permission
    val cameraPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            val photoFile = File(context.cacheDir, "camera_${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", photoFile)
            cameraUri = uri
            cameraLauncher.launch(uri)
        }
    }

    // File picker
    val filePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            val cursor = context.contentResolver.query(it, null, null, null, null)
            val displayName = cursor?.use { c ->
                if (c.moveToFirst()) {
                    val idx = c.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (idx >= 0) c.getString(idx) else "file"
                } else "file"
            } ?: "file"

            val tmpFile = File(context.cacheDir, "file_${System.currentTimeMillis()}_$displayName")
            context.contentResolver.openInputStream(it)?.use { input ->
                tmpFile.outputStream().use { output -> input.copyTo(output) }
            }

            val type = when {
                displayName.endsWith(".pdf", true) -> AttachmentType.PDF
                displayName.endsWith(".docx", true) || displayName.endsWith(".doc", true) -> AttachmentType.DOCUMENT
                displayName.endsWith(".jpg", true) || displayName.endsWith(".jpeg", true) || displayName.endsWith(".png", true) -> AttachmentType.IMAGE
                else -> AttachmentType.TEXT
            }
            attachments.add(Attachment(path = tmpFile.absolutePath, name = displayName, type = type))
        }
    }

    // Auto scroll to bottom
    LaunchedEffect(state.messages.size, state.messages.lastOrNull()?.content) {
        if (state.messages.isNotEmpty()) {
            listState.animateScrollToItem(state.messages.size - 1)
        }
    }

    // Model switcher bottom sheet
    if (showModelSheet) {
        ModalBottomSheet(
            onDismissRequest = { showModelSheet = false },
            sheetState = modelSheetState,
            containerColor = Color(0xFF0D1520),
            dragHandle = {
                Box(
                    modifier = Modifier.padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .width(40.dp).height(4.dp)
                            .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(2.dp))
                    )
                }
            },
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 32.dp)) {
                Text(
                    "Switch Model",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    "Downloaded models for this task",
                    color = Color.White.copy(alpha = 0.4f),
                    fontSize = 13.sp,
                )
                Spacer(Modifier.height(16.dp))

                if (availableModels.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(32.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("No other models downloaded", color = Color.White.copy(alpha = 0.4f))
                    }
                } else {
                    availableModels.forEach { model ->
                        val isCurrent = model.name == modelName
                        ModelSwitchCard(
                            model = model,
                            isSelected = isCurrent,
                            onClick = {
                                if (!isCurrent) {
                                    scope.launch {
                                        modelSheetState.hide()
                                        showModelSheet = false
                                        onSwitchModel(model)
                                    }
                                } else {
                                    scope.launch { modelSheetState.hide(); showModelSheet = false }
                                }
                            },
                        )
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = Color(0xFF0A0F12)) {
                Spacer(Modifier.height(24.dp))
                Text(
                    "Chat History",
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                    color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold,
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Rounded.Add, null, tint = Teal) },
                    label = { Text("New Chat", color = Teal) },
                    selected = false,
                    onClick = { onNewChat(); scope.launch { drawerState.close() } },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
                )
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(state.sessions, key = { it.id }) { session ->
                        val isSelected = session.id == state.currentSessionId
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    session.title,
                                    color = if (isSelected) Color.White else Color.White.copy(alpha = 0.7f),
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    maxLines = 1, overflow = TextOverflow.Ellipsis,
                                )
                            },
                            selected = isSelected,
                            onClick = { onLoadSession(session.id); scope.launch { drawerState.close() } },
                            badge = {
                                IconButton(onClick = { onDeleteSession(session.id) }, modifier = Modifier.size(24.dp)) {
                                    Icon(Icons.Rounded.Delete, null, tint = Color.White.copy(alpha = 0.24f), modifier = Modifier.size(16.dp))
                                }
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = Color.White.copy(alpha = 0.1f),
                                unselectedContainerColor = Color.Transparent,
                            ),
                        )
                    }
                }
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                NavigationDrawerItem(
                    icon = { Icon(Icons.Rounded.Home, null, tint = Color.White.copy(alpha = 0.6f)) },
                    label = { Text("Back to Home", color = Color.White.copy(alpha = 0.6f)) },
                    selected = false,
                    onClick = onBack,
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
                )
                Spacer(Modifier.height(16.dp))
            }
        },
    ) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            AuroraBackground()

            Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

                // ── Top bar ──────────────────────────────────────────────
                Surface(color = Color.White.copy(alpha = 0.05f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Rounded.Menu, "Menu", tint = Color.White)
                        }

                        // Tappable model name — opens model switcher
                        Surface(
                            onClick = { if (availableModels.size > 1) showModelSheet = true },
                            shape = RoundedCornerShape(20.dp),
                            color = Color.White.copy(alpha = if (availableModels.size > 1) 0.08f else 0f),
                            modifier = Modifier.weight(1f),
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            modelName,
                                            color = Color.White,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                        if (availableModels.size > 1) {
                                            Spacer(Modifier.width(4.dp))
                                            Icon(
                                                Icons.Rounded.UnfoldMore,
                                                "Switch model",
                                                tint = Teal,
                                                modifier = Modifier.size(16.dp),
                                            )
                                        }
                                    }
                                    if (state.isPreparing) {
                                        Text("Loading model...", color = Teal, fontSize = 11.sp)
                                    } else if (availableModels.size > 1) {
                                        Text(
                                            "Tap to switch model",
                                            color = Color.White.copy(alpha = 0.35f),
                                            fontSize = 11.sp,
                                        )
                                    }
                                }
                            }
                        }

                        if (state.isGenerating) {
                            IconButton(onClick = onStopGeneration) {
                                Icon(Icons.Rounded.Stop, "Stop", tint = Color(0xFFCF6679))
                            }
                        }
                    }
                }

                // ── Messages ─────────────────────────────────────────────
                LazyColumn(
                    state = listState,
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(state.messages, key = { it.id }) { msg ->
                        ChatBubble(message = msg)
                    }
                    if (state.isGenerating && state.messages.lastOrNull()?.content.isNullOrEmpty()) {
                        item { TypingIndicator() }
                    }
                }

                // ── Attachment preview strip ──────────────────────────────
                if (attachments.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(attachments.size) { index ->
                            val attachment = attachments[index]
                            AttachmentChip(attachment = attachment, onRemove = { attachments.removeAt(index) })
                        }
                    }
                }

                // ── Input bar ────────────────────────────────────────────
                Surface(color = Color.Black.copy(alpha = 0.6f)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Box {
                            IconButton(onClick = { showAttachMenu = true }) {
                                Icon(Icons.Rounded.AddCircle, "Attach", tint = Color.White.copy(alpha = 0.6f))
                            }
                            DropdownMenu(
                                expanded = showAttachMenu,
                                onDismissRequest = { showAttachMenu = false },
                                containerColor = Color(0xFF1A2025),
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Camera", color = Color.White) },
                                    leadingIcon = { Icon(Icons.Rounded.CameraAlt, null, tint = Teal) },
                                    onClick = {
                                        showAttachMenu = false
                                        val hasPerm = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                                        if (hasPerm) {
                                            val photoFile = File(context.cacheDir, "camera_${System.currentTimeMillis()}.jpg")
                                            val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", photoFile)
                                            cameraUri = uri
                                            cameraLauncher.launch(uri)
                                        } else {
                                            cameraPermission.launch(Manifest.permission.CAMERA)
                                        }
                                    },
                                )
                                DropdownMenuItem(
                                    text = { Text("Gallery", color = Color.White) },
                                    leadingIcon = { Icon(Icons.Rounded.Image, null, tint = Color(0xFF64B5F6)) },
                                    onClick = {
                                        showAttachMenu = false
                                        imagePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                    },
                                )
                                DropdownMenuItem(
                                    text = { Text("Files (PDF, TXT, DOC)", color = Color.White) },
                                    leadingIcon = { Icon(Icons.Rounded.Description, null, tint = Color(0xFFFFB74D)) },
                                    onClick = {
                                        showAttachMenu = false
                                        filePicker.launch(arrayOf(
                                            "application/pdf", "text/plain", "text/markdown",
                                            "application/msword",
                                            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                                            "image/*",
                                        ))
                                    },
                                )
                            }
                        }

                        OutlinedTextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            modifier = Modifier.weight(1f),
                            placeholder = {
                                Text(
                                    if (isVisionTask) "Ask about image..." else "Ask AI...",
                                    color = Color.White.copy(alpha = 0.3f),
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedBorderColor = Color.White.copy(alpha = 0.15f),
                                unfocusedBorderColor = Color.White.copy(alpha = 0.08f),
                                cursorColor = Teal,
                            ),
                            shape = RoundedCornerShape(24.dp),
                            maxLines = 5,
                        )

                        Spacer(Modifier.width(8.dp))

                        val canSend = (inputText.isNotBlank() || attachments.isNotEmpty()) && !state.isGenerating && !state.isPreparing

                        IconButton(
                            onClick = {
                                if (canSend) {
                                    onSendMessage(inputText, attachments.toList())
                                    inputText = ""
                                    attachments.clear()
                                }
                            },
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    if (canSend) Brush.linearGradient(listOf(Teal, TealDark))
                                    else Brush.linearGradient(listOf(Color.White.copy(alpha = 0.1f), Color.White.copy(alpha = 0.1f))),
                                    CircleShape,
                                ),
                        ) {
                            Icon(Icons.AutoMirrored.Rounded.Send, "Send", tint = Color.White)
                        }
                    }
                }
            }
        }
    }
}

// ─── Model Switch Card ────────────────────────────────────────────────────────
@Composable
private fun ModelSwitchCard(model: Model, isSelected: Boolean, onClick: () -> Unit) {
    val borderColor = if (isSelected) Teal else Color.White.copy(alpha = 0.08f)
    val bgColor = if (isSelected) Teal.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.04f)

    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = bgColor,
        border = BorderStroke(if (isSelected) 1.5.dp else 1.dp, borderColor),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(
                        if (isSelected) Brush.linearGradient(listOf(Teal, TealDark))
                        else Brush.linearGradient(listOf(Color.White.copy(0.08f), Color.White.copy(0.04f))),
                        RoundedCornerShape(12.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    if (model.llmSupportImage) Icons.Rounded.Visibility else Icons.Rounded.Chat,
                    null,
                    tint = if (isSelected) Color.White else Color.White.copy(0.5f),
                    modifier = Modifier.size(22.dp),
                )
            }

            Spacer(Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    model.name,
                    color = if (isSelected) Color.White else Color.White.copy(0.85f),
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.height(3.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    ModelBadge(model.formattedSize, Color.White.copy(0.4f))
                    if (model.llmSupportImage) ModelBadge("Vision ✦", Teal)
                    if (model.llmSupportAudio) ModelBadge("Audio 🎙️", Color(0xFF9C88FF))
                }
            }

            if (isSelected) {
                Icon(Icons.Rounded.CheckCircle, "Active", tint = Teal, modifier = Modifier.size(22.dp))
            }
        }
    }
}

@Composable
private fun ModelBadge(text: String, color: Color) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = color.copy(alpha = 0.12f),
    ) {
        Text(
            text,
            color = color,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
        )
    }
}

// ─── Attachment Chip ──────────────────────────────────────────────────────────
@Composable
private fun AttachmentChip(attachment: Attachment, onRemove: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White.copy(alpha = 0.08f),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)),
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (attachment.type == AttachmentType.IMAGE) {
                val bmp = remember(attachment.path) { BitmapFactory.decodeFile(attachment.path) }
                if (bmp != null) {
                    Image(
                        bitmap = bmp.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop,
                    )
                }
            } else {
                val (icon, color) = when (attachment.type) {
                    AttachmentType.PDF -> Icons.Rounded.PictureAsPdf to Color(0xFFEF5350)
                    AttachmentType.DOCUMENT -> Icons.Rounded.Description to Color(0xFF42A5F5)
                    AttachmentType.TEXT -> Icons.Rounded.TextSnippet to Color(0xFF66BB6A)
                    else -> Icons.Rounded.AttachFile to Color.White
                }
                Icon(icon, null, tint = color, modifier = Modifier.size(28.dp))
            }
            Spacer(Modifier.width(6.dp))
            Text(
                attachment.name,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.widthIn(max = 100.dp),
            )
            IconButton(onClick = onRemove, modifier = Modifier.size(24.dp)) {
                Icon(Icons.Rounded.Close, "Remove", tint = Color.White.copy(alpha = 0.4f), modifier = Modifier.size(14.dp))
            }
        }
    }
}

// ─── Chat Bubble ──────────────────────────────────────────────────────────────
@Composable
private fun ChatBubble(message: UiChatMessage) {
    val isAssistant = message.role == "assistant"
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isAssistant) Arrangement.Start else Arrangement.End,
    ) {
        if (isAssistant) {
            Box(
                modifier = Modifier.size(32.dp)
                    .background(Brush.linearGradient(listOf(Teal, AccentGreen)), CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Rounded.AutoAwesome, null, tint = Color.White, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.width(8.dp))
        }

        Column(modifier = Modifier.widthIn(max = 300.dp)) {
            if (message.imagePath != null) {
                val bmp = remember(message.imagePath) { BitmapFactory.decodeFile(message.imagePath) }
                if (bmp != null) {
                    Image(
                        bitmap = bmp.asImageBitmap(), contentDescription = null,
                        modifier = Modifier.fillMaxWidth().heightIn(max = 200.dp).clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
            if (message.fileName != null && message.imagePath == null) {
                Surface(shape = RoundedCornerShape(10.dp), color = Color.White.copy(alpha = 0.06f),
                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.08f))) {
                    Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.Description, null, tint = Color(0xFFFFB74D), modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(message.fileName, color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp, maxLines = 1)
                    }
                }
                Spacer(Modifier.height(6.dp))
            }
            if (message.content.isNotEmpty()) {
                Surface(
                    shape = RoundedCornerShape(
                        topStart = 20.dp, topEnd = 20.dp,
                        bottomStart = if (isAssistant) 4.dp else 20.dp,
                        bottomEnd = if (isAssistant) 20.dp else 4.dp,
                    ),
                    color = Color.Transparent,
                ) {
                    Box(modifier = Modifier
                        .background(
                            if (isAssistant) Brush.linearGradient(listOf(DeepOcean.copy(alpha = 0.9f), Color(0xFF0A1825).copy(alpha = 0.95f)))
                            else Brush.linearGradient(listOf(Teal, TealDark)),
                        ).then(
                            if (isAssistant) Modifier.border(1.dp, Color.White.copy(alpha = 0.08f),
                                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 4.dp, bottomEnd = 20.dp))
                            else Modifier
                        ).padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Text(message.content, color = Color.White.copy(alpha = 0.95f), fontSize = 14.sp, lineHeight = 22.sp)
                    }
                }
            }
        }
        if (!isAssistant) Spacer(Modifier.width(40.dp))
    }
}

// ─── Typing Indicator ────────────────────────────────────────────────────────
@Composable
private fun TypingIndicator() {
    val infiniteTransition = rememberInfiniteTransition(label = "typing")
    Row(
        modifier = Modifier.background(Teal.copy(alpha = 0.05f), RoundedCornerShape(20.dp))
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 0..2) {
            val offset by infiniteTransition.animateFloat(
                initialValue = 0f, targetValue = -6f,
                animationSpec = infiniteRepeatable(tween(400, easing = EaseInOut, delayMillis = i * 150), RepeatMode.Reverse),
                label = "dot$i",
            )
            Box(modifier = Modifier.offset(y = offset.dp).size(6.dp).background(Teal, CircleShape))
            if (i < 2) Spacer(Modifier.width(4.dp))
        }
        Spacer(Modifier.width(12.dp))
        Text("PROCESSING...", color = Teal, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
    }
}
