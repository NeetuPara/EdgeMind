package com.edgemind.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edgemind.app.ui.components.AuroraBackground
import com.edgemind.app.ui.theme.Teal

private const val DEFAULT_PIN = "1234"

@Composable
fun PinScreen(onAuthenticated: () -> Unit) {
    var pin by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        AuroraBackground()

        Column(
            modifier = Modifier.fillMaxSize().padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(Modifier.weight(0.3f))

            Box(
                modifier = Modifier.size(80.dp).background(
                    Brush.linearGradient(listOf(Teal, Color(0xFFBAC7B2))),
                    CircleShape,
                ),
                contentAlignment = Alignment.Center,
            ) {
                Text("🔒", fontSize = 32.sp)
            }

            Spacer(Modifier.height(32.dp))

            Text("Enter PIN", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("Secure access to EdgeMind", color = Color.White.copy(alpha = 0.5f), fontSize = 14.sp)

            Spacer(Modifier.height(40.dp))

            // PIN dots
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                for (i in 0..3) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(
                                if (i < pin.length) Teal
                                else Color.White.copy(alpha = 0.15f)
                            ),
                    )
                }
            }

            if (error) {
                Spacer(Modifier.height(16.dp))
                Text("Incorrect PIN", color = Color(0xFFCF6679), fontSize = 14.sp)
            }

            Spacer(Modifier.height(40.dp))

            // Number pad
            val keys = listOf(
                listOf("1", "2", "3"),
                listOf("4", "5", "6"),
                listOf("7", "8", "9"),
                listOf("", "0", "⌫"),
            )

            for (row in keys) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    for (key in row) {
                        if (key.isEmpty()) {
                            Spacer(Modifier.size(72.dp))
                        } else {
                            FilledTonalButton(
                                onClick = {
                                    error = false
                                    if (key == "⌫") {
                                        if (pin.isNotEmpty()) pin = pin.dropLast(1)
                                    } else if (pin.length < 4) {
                                        pin += key
                                        if (pin.length == 4) {
                                            if (pin == DEFAULT_PIN) {
                                                onAuthenticated()
                                            } else {
                                                error = true
                                                pin = ""
                                            }
                                        }
                                    }
                                },
                                modifier = Modifier.size(72.dp),
                                shape = CircleShape,
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = Color.White.copy(alpha = 0.08f),
                                    contentColor = Color.White,
                                ),
                            ) {
                                Text(key, fontSize = 24.sp, fontWeight = FontWeight.W500)
                            }
                        }
                    }
                }
                Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.weight(0.3f))
        }
    }
}
