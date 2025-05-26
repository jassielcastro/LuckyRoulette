package org.ajcm.luckyroulette.ui.winner

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ajcm.luckyroulette.theme.rouletteColors
import org.ajcm.luckyroulette.ui.models.RouletteItem
import org.ajcm.luckyroulette.ui.roulette.RouletteSpinnerView

@Composable
fun WinnerView(
    winner: RouletteItem,
    topics: List<String>,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val animatedColor by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(1500, easing = LinearEasing),
            RepeatMode.Reverse
        ),
    )

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 4.dp,
            Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.primary
                ),
                start = Offset(0f, 0f),
                end = Offset( 1000 * animatedColor, 0f)
            )
        )
    ) {
        WinnerContent(winner, topics)
    }
}

@Composable
fun WinnerContent(
    winner: RouletteItem,
    topics: List<String>,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "You’re the lucky one!",
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Normal,
        )

        Text(
            text = "The wheel has spoken — it’s your time to shine!",
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Normal,
        )

        Text(
            text = "!!${winner.value}!!",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.ExtraBold,
        )

        Text(
            text = "Your Topic is:",
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.ExtraBold,
        )

        TopicRouletteView(
            topics = topics,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
fun TopicRouletteView(
    modifier: Modifier,
    topics: List<String>
) {
    var topic by remember { mutableStateOf<String?>(null) }

    AnimatedContent(
        targetState = topic,
        transitionSpec = {
            fadeIn() + slideInVertically(animationSpec = spring(
                dampingRatio = 0.8f,
                stiffness = Spring.StiffnessLow
            ), initialOffsetY = { fullHeight -> fullHeight }) togetherWith
                    fadeOut(animationSpec = tween(200))
        },
        modifier = modifier
    ) { topicState ->
        if (topicState == null && topics.isNotEmpty()) {
            RouletteSpinnerView(
                items = topics.buildRouletteTopics(),
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onSpinEnd = { selected ->
                    topic = selected.value
                }
            )
        } else {
            Text(
                text = ">> ${topic ?: "Anything"} <<",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}

private fun List<String>.buildRouletteTopics(): List<RouletteItem> {
    return this.mapIndexed { index, value ->
        RouletteItem(
            value = value,
            color = rouletteColors[index % rouletteColors.size]
        )
    }
}
