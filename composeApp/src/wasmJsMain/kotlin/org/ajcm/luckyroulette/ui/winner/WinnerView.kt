package org.ajcm.luckyroulette.ui.winner

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ajcm.luckyroulette.theme.surfaceBrightDark
import org.ajcm.luckyroulette.theme.surfaceDimDark
import org.ajcm.luckyroulette.ui.models.RouletteItem
import org.ajcm.luckyroulette.ui.roulette.RouletteSpinnerView

@Composable
fun WinnerView(
    winner: RouletteItem,
    topics: List<String>,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
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
                .fillMaxSize(0.9f)
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
                    .fillMaxSize()
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
            color = if (index % 2 == 0) {
                surfaceDimDark
            } else {
                surfaceBrightDark
            }
        )
    }
}
