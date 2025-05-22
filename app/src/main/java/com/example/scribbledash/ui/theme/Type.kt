package com.example.scribbledash.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.scribbledash.R


val BagelFatOne = FontFamily(
    Font(
        resId = R.font.bagel_fat_one_regular,
        weight = FontWeight.Normal,
    )
)

val Outfit = FontFamily(
    Font(
        resId = R.font.outfit_regular,
        weight = FontWeight.Normal,
    ),
    Font(
        resId = R.font.outfit_medium,
        weight = FontWeight.Medium,
    ),
    Font(
        resId = R.font.outfit_semibold,
        weight = FontWeight.SemiBold,
    )
)

// Set of Material typography styles to start with
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(
        fontFamily = BagelFatOne,
        fontSize = 66.sp,
        lineHeight = 80.sp,
        color = onBackgroundLight
    ),
    displayMedium = baseline.displayMedium.copy(
        fontFamily = BagelFatOne,
        fontSize = 40.sp,
        lineHeight = 44.sp,
        color = onBackgroundLight
    ),
    headlineLarge = baseline.headlineLarge.copy(
        fontFamily = BagelFatOne,
        fontSize = 34.sp,
        lineHeight = 48.sp,
        color = onBackgroundLight
    ),
    headlineMedium = baseline.headlineMedium.copy(
        fontFamily = BagelFatOne,
        fontSize = 26.sp,
        lineHeight = 30.sp,
        color = onBackgroundLight
    ),
    headlineSmall = baseline.headlineSmall.copy(
        fontFamily = BagelFatOne,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        color = onBackgroundLight
    ),
    bodyLarge = baseline.bodyLarge.copy(
        fontFamily = Outfit,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        color = onBackgroundVariant
    ),
    bodyMedium = baseline.bodyMedium.copy(
        fontFamily = Outfit,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        color = onBackgroundVariant
    ),
    bodySmall = baseline.bodySmall.copy(
        fontFamily = Outfit,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        color = onBackgroundVariant
    ),
    labelLarge = baseline.labelLarge.copy(
        fontFamily = Outfit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        color = onBackgroundVariant
    ),
    labelMedium = baseline.labelMedium.copy(
        fontFamily = Outfit,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = onBackgroundVariant
    ),
    labelSmall = baseline.labelSmall.copy(
        fontFamily = Outfit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        color = onBackgroundVariant
    )
)

