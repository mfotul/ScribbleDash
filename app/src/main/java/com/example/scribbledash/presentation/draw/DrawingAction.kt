package com.example.scribbledash.presentation.draw

import androidx.compose.ui.geometry.Offset

sealed interface DrawingAction {
    data object OnNewPathStart : DrawingAction
    data class OnDraw(val offset: Offset) : DrawingAction
    data object OnPathEnd : DrawingAction
    data object OnClearCanvas : DrawingAction
    data object OnUndo : DrawingAction
    data object OnRedo : DrawingAction
    data object OnPreviewFalse : DrawingAction
    data class onSetCanvas(val canvas: Int) : DrawingAction
}