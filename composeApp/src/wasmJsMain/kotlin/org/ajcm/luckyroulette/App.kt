package org.ajcm.luckyroulette

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ajcm.luckyroulette.theme.LuckyRouletteTheme
import org.ajcm.luckyroulette.theme.rouletteColors
import org.ajcm.luckyroulette.ui.components.LuckyTopAppBar
import org.ajcm.luckyroulette.ui.models.RouletteItem
import org.ajcm.luckyroulette.ui.participants.ParticipantsView
import org.ajcm.luckyroulette.ui.roulette.RouletteContainerView
import org.ajcm.luckyroulette.ui.winner.WinnerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val topics = remember { mutableStateListOf<String>() }
    val participants = remember { mutableStateListOf<RouletteItem>() }
    var winnerParticipant by remember { mutableStateOf<RouletteItem?>(null) }

    LaunchedEffect(participants.size) {
        val newParticipants = participants.mapIndexed { index, item ->
            item.copy(color = rouletteColors[index % rouletteColors.size])
        }
        participants.clear()
        participants.addAll(newParticipants)
    }

    LuckyRouletteTheme {
        Scaffold(
            topBar = {
                LuckyTopAppBar()
            }
        ) { innerPadding ->
            LuckyRouletteAdaptativeContent(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                onAddParticipant = { participantName ->
                    participants.add(
                        RouletteItem(
                            value = participantName,
                        )
                    )
                },
                onAddTopic = { topicText ->
                    if (topicText !in topics) {
                        topics.add(topicText)
                    }
                },
                items = participants,
                onSpinEnd = { winner ->
                    winnerParticipant = winner
                },
                onClear = {
                    participants.clear()
                }
            )

            if (winnerParticipant != null) {
                BasicAlertDialog(
                    onDismissRequest = {
                        participants.remove(winnerParticipant!!)
                        winnerParticipant = null
                    },
                ) {
                    WinnerView(
                        winner = winnerParticipant!!,
                        topics = topics
                    )
                }
            }
        }
    }
}

@Composable
fun LuckyRouletteAdaptativeContent(
    modifier: Modifier,
    items: List<RouletteItem>,
    onAddParticipant: (String) -> Unit,
    onAddTopic: (String) -> Unit,
    onSpinEnd: (RouletteItem) -> Unit,
    onClear: () -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val isCompact = maxWidth < 920.dp

        if (isCompact) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
            ) {
                ParticipantsSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    isCompact = isCompact,
                    onAddParticipant = onAddParticipant,
                    onAddTopic = onAddTopic
                )

                RouletteSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    items = items,
                    onSpinEnd = onSpinEnd,
                    onClear = onClear
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                ParticipantsSection(
                    modifier = Modifier
                        .fillMaxWidth(0.35f)
                        .fillMaxHeight()
                        .padding(16.dp),
                    isCompact = isCompact,
                    onAddParticipant = onAddParticipant,
                    onAddTopic = onAddTopic
                )

                RouletteSection(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    items = items,
                    onSpinEnd = onSpinEnd,
                    onClear = onClear
                )
            }
        }
    }
}

@Composable
fun ParticipantsSection(
    modifier: Modifier = Modifier,
    isCompact: Boolean,
    onAddParticipant: (String) -> Unit,
    onAddTopic: (String) -> Unit
) {
    ParticipantsView(
        modifier = modifier,
        isCompact = isCompact,
        onAddParticipant = onAddParticipant,
        onAddTopic = onAddTopic
    )
}

@Composable
fun RouletteSection(
    modifier: Modifier = Modifier,
    items: List<RouletteItem>,
    onSpinEnd: (RouletteItem) -> Unit,
    onClear: () -> Unit
) {
    RouletteContainerView(
        modifier = modifier,
        items = items,
        onSpinEnd = onSpinEnd,
        onClear = onClear
    )
}
