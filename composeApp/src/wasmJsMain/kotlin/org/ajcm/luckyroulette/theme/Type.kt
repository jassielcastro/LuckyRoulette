package org.ajcm.luckyroulette.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import luckyroulette.composeapp.generated.resources.Res
import luckyroulette.composeapp.generated.resources.quicksand
import org.jetbrains.compose.resources.Font

@Composable
fun LuckyFontFamily() = FontFamily(
    Font(Res.font.quicksand, weight = FontWeight.Light),
    Font(Res.font.quicksand, weight = FontWeight.Normal),
    Font(Res.font.quicksand, weight = FontWeight.Medium),
    Font(Res.font.quicksand, weight = FontWeight.SemiBold),
    Font(Res.font.quicksand, weight = FontWeight.Bold)
)

@Composable
fun LuckyTypography() = Typography().run {
    val fontFamily = LuckyFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily =  fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}
