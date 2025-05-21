package com.example.scribbledash.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.example.scribbledash.R
import com.example.scribbledash.ui.theme.MutedTaupe
import com.example.scribbledash.ui.theme.ScribbleDashTheme
import kotlin.math.abs

@Composable
fun DrawScreen(
    paths: List<PathData>,
    undoPaths: List<PathData>,
    currentPath: PathData?,
    onAction: (DrawingAction) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val isUndoEnabled = remember(paths) { derivedStateOf { paths.isNotEmpty() } }
    val isRedoEnabled = remember(undoPaths) { derivedStateOf { undoPaths.isNotEmpty() } }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = onCloseClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_cancel),
                tint = MutedTaupe,
                contentDescription = stringResource(R.string.close),
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.time_to_draw),
                style = MaterialTheme.typography.displayLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = MutedTaupe,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(Color.White)
            ) {
                CanvasBackground()
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .clipToBounds()
                        .pointerInput(true) {
                            detectDragGestures(
                                onDragStart = {
                                    onAction(DrawingAction.OnNewPathStart)
                                },
                                onDragEnd = {
                                    onAction(DrawingAction.OnPathEnd)
                                },
                                onDrag = { change, _ ->
                                    onAction(DrawingAction.OnDraw(change.position))
                                },
                                onDragCancel = {
                                    onAction(DrawingAction.OnPathEnd)
                                }
                            )
                        }
                ) {
                    paths.fastForEach { pathData ->
                        drawPath(
                            path = pathData.path,
                            color = pathData.color
                        )
                    }
                    currentPath?.let { pathData ->
                        drawPath(
                            path = pathData.path,
                            color = pathData.color
                        )
                    }
                }
            }
        }

        DrawScreenBottomBar(
            onUndoClick = { onAction(DrawingAction.OnUndo) },
            onRedoClick = { onAction(DrawingAction.OnRedo) },
            onClearCanvasClick = { onAction(DrawingAction.OnClearCanvas) },
            isUndoEnabled = isUndoEnabled.value,
            isRedoEnabled = isRedoEnabled.value,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun CanvasBackground() {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Calculate the size of each square
        val squareWidth = canvasWidth / 3
        val squareHeight = canvasHeight / 3

        // Define the color for the grid lines
        val lineColor = MutedTaupe
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

private fun DrawScope.drawPath(
    path: List<Offset>,
    color: Color,
    strokeWidth: Float = 10f
) {
    val smoothedPath = Path().apply {
        if (path.isNotEmpty()) {
            moveTo(path.first().x, path.first().y)

            val smoothness = 5
            for (i in 1..path.lastIndex) {
                val from = path[i - 1]
                val to = path[i]
                val dx = abs(from.x - to.x)
                val dy = abs(from.y - to.y)
                if (dx > smoothness || dy > smoothness) {
                    quadraticTo(
                        x1 = (from.x + to.x) / 2,
                        y1 = (from.y + to.y) / 2,
                        x2 = to.x,
                        y2 = to.y
                    )
                }
            }
        }
    }
    drawPath(
        path = smoothedPath,
        color = color,
        style = Stroke(
            width = strokeWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )
}

@Composable
@Preview(showBackground = true)
fun DrawScreenPreview() {
    ScribbleDashTheme {
        DrawScreen(
            paths = emptyList(),
            undoPaths = emptyList(),
            currentPath = null,
            onAction = {},
            onCloseClick = {}
        )
    }
}