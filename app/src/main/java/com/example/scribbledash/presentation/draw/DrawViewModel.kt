package com.example.scribbledash.presentation.draw

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class PathData(
    val id: String,
    val color: Color = Color.Black,
    val path: List<Offset>
)

class DrawViewModel : ViewModel() {

    private val _state = MutableStateFlow(DrawingState())
    val state = _state.asStateFlow()


    fun onAction(action: DrawingAction) {
        when (action) {
            DrawingAction.OnNewPathStart -> onNewPathStart()
            DrawingAction.OnClearCanvas -> onClearCanvas()
            is DrawingAction.OnDraw -> onDraw(action.offset)
            DrawingAction.OnPathEnd -> onPathEnd()
            DrawingAction.OnRedo -> onRedo()
            DrawingAction.OnUndo -> onUndo()
            DrawingAction.OnPreviewFalse -> onPreviewFalse()
            is DrawingAction.onSetCanvas -> onSetCanvas(action.canvas)
        }
    }

    private fun onSetCanvas(canvas: Int) {
        _state.update {
            it.copy(
                canvasExample = canvas
            )
        }
    }

    private fun onPreviewFalse() {
        _state.update {
            it.copy(
                isPreview = false
            )
        }
    }

    private fun onUndo() {
        if (state.value.paths.isEmpty()) return
        val undoPaths = if (state.value.undoPaths.size == 5)
            state.value.undoPaths.drop(1)
        else state.value.undoPaths

        _state.update {
            it.copy(
                paths = it.paths.dropLast(1),
                undoPaths = undoPaths + it.paths.last()
            )
        }
    }

    private fun onRedo() {
        if (state.value.undoPaths.isEmpty()) return
        _state.update {
            it.copy(
                paths = it.paths + it.undoPaths.last(),
                undoPaths = it.undoPaths.dropLast(1)
            )
        }
    }

    private fun onPathEnd() {
        val currentPathData = state.value.currentPath ?: return
        _state.update {
            it.copy(
                currentPath = null,
                paths = it.paths + currentPathData
            )
        }
    }

    private fun onDraw(offset: Offset) {
        val currentPathData = state.value.currentPath ?: return
        _state.update {
            it.copy(
                currentPath = currentPathData.copy(
                    path = currentPathData.path + offset
                )
            )
        }
    }

    private fun onClearCanvas() {
        _state.update {
            it.copy(
                currentPath = null,
                paths = emptyList(),
                undoPaths = emptyList()
            )
        }
    }

    private fun onNewPathStart() {
        _state.update {
            it.copy(
                currentPath = PathData(
                    id = System.currentTimeMillis().toString(),
                    path = emptyList(),
                ),
                undoPaths = emptyList()
            )
        }
    }
}