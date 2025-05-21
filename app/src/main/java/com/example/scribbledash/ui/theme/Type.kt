package com.example.scribbledash.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.scribbledash.R.array.com_google_android_gms_fonts_certs


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = com_google_android_gms_fonts_certs
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Bagel Fat One"),
        fontProvider = provider)
)

val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Outfit"),
        fontProvider = provider)
)

// Set of Material typography styles to start with
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(
        fontFamily = displayFontFamily,
        lineHeight = 44.sp,
        fontSize = 40.sp,
        color = RusticRed
    ),
    displayMedium = baseline.displayMedium.copy(
        fontFamily = displayFontFamily,
        lineHeight = 30.sp,
        fontSize = 26.sp,
        color = RusticRed
    ),
    displaySmall = baseline.displaySmall.copy(
        fontFamily = displayFontFamily,
        lineHeight = 26.sp,
        fontSize = 18.sp,
        color = RusticRed
    ),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(
        fontFamily = bodyFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        color = GraniteGray
    ),
    bodyMedium = baseline.bodyMedium.copy(
        fontFamily = bodyFontFamily,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        color = GraniteGray
    ),
    bodySmall = baseline.bodySmall.copy(
        fontFamily = bodyFontFamily,
        lineHeight = 16.sp,
        fontSize = 12.sp,
        color = GraniteGray
    ),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily)
)