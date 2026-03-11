package com.edgemind.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Edgemind color palette
val Teal = Color(0xFF468F82)
val TealDark = Color(0xFF2E7063)
val DeepOcean = Color(0xFF153664)
val DarkSurface = Color(0xFF0A0F12)
val DarkBackground = Color(0xFF000000)
val PrimaryBlue = Color(0xFF0D93F2)
val AccentGreen = Color(0xFFBAC7B2)

private val DarkColorScheme = darkColorScheme(
    primary = Teal,
    onPrimary = Color.White,
    primaryContainer = TealDark,
    secondary = PrimaryBlue,
    onSecondary = Color.White,
    background = DarkBackground,
    onBackground = Color.White,
    surface = DarkSurface,
    onSurface = Color.White,
    surfaceVariant = Color(0xFF1E1E1E),
    onSurfaceVariant = Color.White.copy(alpha = 0.7f),
    outline = Color.White.copy(alpha = 0.1f),
    error = Color(0xFFCF6679),
)

@Composable
fun EdgeMindTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography(),
        content = content,
    )
}
