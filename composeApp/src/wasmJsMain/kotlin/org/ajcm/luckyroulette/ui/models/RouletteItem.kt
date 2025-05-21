package org.ajcm.luckyroulette.ui.models

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
data class RouletteItem(
    val value: String,
    val color: Color = Color.Unspecified
)
