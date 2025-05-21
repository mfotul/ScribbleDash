package com.example.scribbledash.presentation

import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Stable
data class DrawingState(
    val currentPath: PathData? = null,
    val undoPaths: List<PathData> = emptyList(),
    val paths: List<PathData> = emptyList()
)

data class PathData(
    val id: String,
    val color: Color = Color.Black,
    val path: List<Offset>
)

sealed interface DrawingAction {
    data object OnNewPathStart : DrawingAction
    data class OnDraw(val offset: Offset) : DrawingAction
    data object OnPathEnd : DrawingAction
    data object OnClearCanvas : DrawingAction
    data object OnUndo : DrawingAction
    data object OnRedo : DrawingAction
}

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
                    path = emptyList()
                )
            )
        }
    }
}