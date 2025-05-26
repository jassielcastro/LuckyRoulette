package org.ajcm.luckyroulette.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
)

@Composable
fun LuckyRouletteTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colorScheme = lightScheme,
        typography = LuckyTypography(),
        content = content
    )
}
