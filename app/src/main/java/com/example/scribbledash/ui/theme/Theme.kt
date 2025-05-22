package com.example.scribbledash.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    onPrimary = onPrimaryLight,
    primary = primaryLight,
    secondary = secondaryLight,
    tertiaryContainer = tertiaryContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    onSurfaceVariant = onSurfaceVariantLight,
    error = errorLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainerLowest = surfaceContainerLowestLight
)

@Composable
fun ScribbleDashTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography,
        content = content
    )
}