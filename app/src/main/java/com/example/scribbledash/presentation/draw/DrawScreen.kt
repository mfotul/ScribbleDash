package com.example.scribbledash.presentation.draw

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.example.scribbledash.R
import com.example.scribbledash.presentation.draw.components.DrawScreenBottomBar
import com.example.scribbledash.presentation.draw.components.DrawScreenMain
import com.example.scribbledash.ui.theme.ScribbleDashTheme
import kotlinx.coroutines.delay
import kotlin.math.abs

@Composable
fun DrawScreen(
    paths: List<PathData>,
    undoPaths: List<PathData>,
    currentPath: PathData?,
    isPreview: Boolean,
    @DrawableRes canvasExample:  Int,
    onAction: (DrawingAction) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val isUndoEnabled = remember(paths) { derivedStateOf { paths.isNotEmpty() } }
    val isRedoEnabled = remember(undoPaths) { derivedStateOf { undoPaths.isNotEmpty() } }
    var seconds by remember { mutableIntStateOf(3) }

    LaunchedEffect(seconds) {
        if (seconds > 0) {
            delay(1000L)
            seconds--
        } else
            onAction(DrawingAction.OnPreviewFalse)
    }

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
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = stringResource(R.string.close),
            )
        }

        if (isPreview) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y= (-60).dp)
            ) {
                DrawScreenMain(
                    text = stringResource(R.string.ready_set),
                ) {
                    Image(
                        painter = painterResource(canvasExample),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )

                }
                Text(
                    text = stringResource(R.string.example),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
            val countDownText = pluralStringResource(
                id = R.plurals.seconds_remaining,
                count = seconds,
                seconds
            )
            Text(
                text = countDownText,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 80.dp)
            )
        } else {
            DrawScreenMain(
                text = stringResource(R.string.time_to_draw),
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y= (-60).dp)
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
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
            isPreview = true,
            canvasExample = R.drawable.whale,
            onAction = {},
            onCloseClick = {}
        )
    }
}