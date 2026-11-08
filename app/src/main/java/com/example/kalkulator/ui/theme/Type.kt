package com.example.kalkulator.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.kalkulator.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Inter")

val AppFontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)

private val defaultTypography = Typography()

val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum"),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFontFamily, fontFeatureSettings = "tnum")
)