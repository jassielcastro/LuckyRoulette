package org.ajcm.luckyroulette.theme

import androidx.compose.ui.graphics.Color

val primaryLight = Color(0xFF4483AB) // Lapras
val onPrimaryLight = Color(0xFFFFFFFF)
val onPrimaryContainerLight = Color(0xFFFFFFFF)
val secondaryLight = Color(0xFFD6C3A1) // Lapras
val backgroundLight = Color(0xFF030711)
val onBackgroundLight = Color(0xFFFFFFFF)

val rouletteColors = listOf(
    primaryLight,
    secondaryLight,
    primaryLight.copy(alpha = 0.6f),
    secondaryLight.copy(alpha = 0.6f),
)
