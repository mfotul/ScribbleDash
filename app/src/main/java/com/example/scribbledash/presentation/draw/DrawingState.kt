package com.example.scribbledash.presentation.draw

import android.support.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.example.scribbledash.R

@Stable
data class DrawingState(
    val currentPath: PathData? = null,
    val undoPaths: List<PathData> = emptyList(),
    val paths: List<PathData> = emptyList(),
    val isPreview: Boolean = true,
    @DrawableRes val canvasExample: Int = R.drawable.circle,
)