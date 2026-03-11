package com.edgemind.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.Chat
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edgemind.app.data.DownloadStatusType
import com.edgemind.app.data.Model
import com.edgemind.app.data.TaskIds
import com.edgemind.app.ui.components.AuroraBackground
import com.edgemind.app.ui.theme.Teal
import com.edgemind.app.ui.theme.TealDark
import com.edgemind.app.viewmodel.ModelManagerUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelListScreen(
    taskId: String,
    taskLabel: String,
    state: ModelManagerUiState,
    onBack: () -> Unit,
    onDownload: (Model) -> Unit,
    onCancel: (Model) -> Unit,
    onChat: (Model) -> Unit,
    onDelete: (Model) -> Unit,
) {
    val models = state.tasks.find { it.id == taskId }?.models ?: emptyList()

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        AuroraBackground()

        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            // Top bar
            TopAppBar(
                title = { Text(taskLabel, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White,
                ),
            )

            if (state.loading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Teal)
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(models, key = { it.name }) { model ->
                        ModelCard(
                            model = model,
                            taskId = taskId,
                            downloadStatus = state.downloadStatuses[model.name],
                            onDownload = { onDownload(model) },
                            onCancel = { onCancel(model) },
                            onChat = { onChat(model) },
                            onDelete = { onDelete(model) },
                        )
                    }

                    item {
                        Spacer(Modifier.height(40.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ModelCard(
    model: Model,
    taskId: String,
    downloadStatus: com.edgemind.app.data.DownloadStatus?,
    onDownload: () -> Unit,
    onCancel: () -> Unit,
    onChat: () -> Unit,
    onDelete: () -> Unit,
) {
    val status = downloadStatus?.status ?: DownloadStatusType.NOT_DOWNLOADED
    val isReady = status == DownloadStatusType.SUCCEEDED
    val isDownloading = status == DownloadStatusType.IN_PROGRESS

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.06f)),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Header row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(model.name, color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Spacer(Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Badge("Text")
                        if (model.llmSupportImage) Badge("Vision")
                        if (model.llmSupportAudio) Badge("Audio")
                    }
                }
                Text(model.formattedSize, color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp, fontWeight = FontWeight.W600)
            }

            Spacer(Modifier.height(12.dp))

            // Description
            if (model.info.isNotBlank()) {
                Text(model.info, color = Color.White.copy(alpha = 0.55f), fontSize = 13.sp, lineHeight = 20.sp, maxLines = 3, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(12.dp))
            }

            // Specs Info
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.Memory, contentDescription = null, tint = Color.White.copy(alpha = 0.4f), modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(6.dp))
                Text("Needs ${model.minDeviceMemoryInGb}+ GB RAM", color = Color.White.copy(alpha = 0.4f), fontSize = 12.sp)
                
                Spacer(Modifier.width(16.dp))
                
                Icon(Icons.Rounded.DataUsage, contentDescription = null, tint = Color.White.copy(alpha = 0.4f), modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(6.dp))
                Text("Context: ${model.llmMaxToken} tokens", color = Color.White.copy(alpha = 0.4f), fontSize = 12.sp)
            }

            Spacer(Modifier.height(16.dp))

            // Action button
            when {
                isReady -> {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = onChat,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Teal),
                        ) {
                            Icon(Icons.AutoMirrored.Rounded.Chat, null, Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Chat", fontWeight = FontWeight.Bold)
                        }
                        OutlinedButton(
                            onClick = onDelete,
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFCF6679)),
                        ) {
                            Icon(Icons.Rounded.Delete, null, Modifier.size(18.dp))
                        }
                    }
                }
                isDownloading -> {
                    Column {
                        LinearProgressIndicator(
                            progress = { downloadStatus?.progressPercent ?: 0f },
                            modifier = Modifier.fillMaxWidth().height(6.dp).padding(vertical = 2.dp),
                            color = Teal,
                            trackColor = Teal.copy(alpha = 0.15f),
                        )
                        Spacer(Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                "${((downloadStatus?.progressPercent ?: 0f) * 100).toInt()}% • ${downloadStatus?.formattedSpeed ?: ""}",
                                color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp,
                            )
                            TextButton(onClick = onCancel) {
                                Text("Cancel", color = Color(0xFFCF6679), fontSize = 12.sp)
                            }
                        }
                    }
                }
                status == DownloadStatusType.FAILED -> {
                    Column {
                        Text("Download failed: ${downloadStatus?.errorMessage ?: ""}", color = Color(0xFFCF6679), fontSize = 12.sp)
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = onDownload,
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f)),
                        ) {
                            Icon(Icons.Rounded.Refresh, null, Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Retry Download", color = Color.White)
                        }
                    }
                }
                else -> {
                    Button(
                        onClick = onDownload,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f)),
                    ) {
                        Icon(Icons.Rounded.Download, null, tint = Color.White, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Download", color = Color.White, fontWeight = FontWeight.W600)
                    }
                }
            }
        }
    }
}

@Composable
private fun Badge(text: String) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = when (text) {
            "Vision" -> Color(0xFF153664).copy(alpha = 0.5f)
            "Audio" -> Color(0xFF6B4C8A).copy(alpha = 0.5f)
            else -> Teal.copy(alpha = 0.2f)
        },
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            color = Color.White.copy(alpha = 0.9f),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
