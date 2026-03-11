package com.edgemind.app.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.cos
import kotlin.math.sin

/**
 * Animated aurora gradient background — three floating blurred circles
 * that drift around slowly, creating a premium ambient effect.
 */
@Composable
fun AuroraBackground(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "aurora")

    val phase1 = infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(20000, easing = LinearEasing)),
        label = "phase1",
    )
    val phase2 = infiniteTransition.animateFloat(
        initialValue = 120f, targetValue = 480f,
        animationSpec = infiniteRepeatable(tween(25000, easing = LinearEasing)),
        label = "phase2",
    )
    val phase3 = infiniteTransition.animateFloat(
        initialValue = 240f, targetValue = 600f,
        animationSpec = infiniteRepeatable(tween(30000, easing = LinearEasing)),
        label = "phase3",
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        val colors = listOf(
            Color(0xFF468F82).copy(alpha = 0.15f),
            Color(0xFF153664).copy(alpha = 0.12f),
            Color(0xFF0D93F2).copy(alpha = 0.10f),
        )
        val phases = listOf(phase1.value, phase2.value, phase3.value)
        val radii = listOf(w * 0.4f, w * 0.35f, w * 0.3f)

        for (i in 0..2) {
            val rad = Math.toRadians(phases[i].toDouble())
            val cx = w / 2 + (w * 0.25f * cos(rad)).toFloat()
            val cy = h / 2 + (h * 0.2f * sin(rad * 1.3)).toFloat()

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(colors[i], Color.Transparent),
                    center = Offset(cx, cy),
                    radius = radii[i],
                ),
                center = Offset(cx, cy),
                radius = radii[i],
            )
        }
    }
}
