package org.ajcm.luckyroulette.ui.roulette

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ajcm.luckyroulette.ui.components.LuckyEmptyStateView
import org.ajcm.luckyroulette.ui.models.RouletteItem

@Composable
fun RouletteContainerView(
    modifier: Modifier,
    items: List<RouletteItem>,
    onSpinEnd: (RouletteItem) -> Unit = {},
    onClear: () -> Unit = {},
) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Your voice matters!",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(bottom = 8.dp)
        )

        Text(
            text = "Let the Lucky Roulette decide who’s up next!",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
        )

        AnimatedContent(
            targetState = items.isEmpty()
        ) { isEmpty ->
            if (isEmpty) {
                LuckyEmptyStateView(
                    modifier = Modifier
                        .padding(14.dp),
                )
            } else {
                RouletteSpinnerComponent(
                    items = items,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    onSpinEnd = onSpinEnd,
                    onClear = onClear
                )
            }
        }
    }
}

@Composable
fun RouletteSpinnerComponent(
    items: List<RouletteItem>,
    modifier: Modifier = Modifier,
    onSpinEnd: (RouletteItem) -> Unit = {},
    onClear: () -> Unit = {},
) {
    Box(
        modifier = modifier,
    ) {
        Button(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.TopEnd),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
            onClick = {
                onClear()
            }
        ) {
            Text(
                text = "Reset roulette",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
            )
        }

        RouletteSpinnerView(
            items = items,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxSize(0.8f)
                .padding(16.dp)
                .align(Alignment.Center),
            onSpinEnd = onSpinEnd
        )
    }
}
