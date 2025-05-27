package com.example.scribbledash.presentation.draw.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp

@Composable
fun CanvasBackground() {
    val lineColor = MaterialTheme.colorScheme.background
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Calculate the size of each square
        val squareWidth = canvasWidth / 3
        val squareHeight = canvasHeight / 3

        // Define the color for the grid lines

        val strokeWidth = 1.dp.toPx() // Convert dp to pixels for stroke width

        // Draw horizontal lines
        for (i in 1 until 3) {
            val y = i * squareHeight
            drawLine(
                color = lineColor,
                start = Offset(0f, y),
                end = Offset(canvasWidth, y),
                strokeWidth = strokeWidth
            )
        }

        // Draw vertical lines
        for (i in 1 until 3) {
            val x = i * squareWidth
            drawLine(
                color = lineColor,
                start = Offset(x, 0f),
                end = Offset(x, canvasHeight),
                strokeWidth = strokeWidth
            )
        }
    }
}
