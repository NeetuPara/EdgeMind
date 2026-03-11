package com.edgemind.app.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PrimaryBlue = Color(0xFF0D93F2)
private val BackgroundDark = Color(0xFF101B22)

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        for (i in 0..100) {
            kotlinx.coroutines.delay(20)
            progress = i / 100f
        }
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(PrimaryBlue.copy(alpha = 0.1f), BackgroundDark, BackgroundDark),
                    radius = 800f,
                )
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(Modifier.weight(1f))

            // Logo placeholder
            Box(
                modifier = Modifier.size(120.dp).background(
                    Brush.linearGradient(listOf(Color(0xFF468F82), Color(0xFFBAC7B2))),
                    RoundedCornerShape(28.dp),
                ),
                contentAlignment = Alignment.Center,
            ) {
                Text("EM", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(48.dp))

            Text(
                "EdgeMind",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "GET LIFE IN CONTROL",
                color = PrimaryBlue.copy(alpha = 0.8f),
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                letterSpacing = 4.sp,
            )

            Spacer(Modifier.weight(1f))

            // Progress section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("Initializing Core", color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp)
                Text("${(progress * 100).toInt()}%", color = PrimaryBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(3.dp).clip(RoundedCornerShape(2.dp)),
                color = PrimaryBlue,
                trackColor = PrimaryBlue.copy(alpha = 0.1f),
            )

            Spacer(Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.Lock, contentDescription = null, tint = Color.White.copy(alpha = 0.38f), modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(8.dp))
                Text("SECURE PIN ACCESS ENABLED", color = Color.White.copy(alpha = 0.38f), fontSize = 10.sp, fontWeight = FontWeight.W500)
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}
