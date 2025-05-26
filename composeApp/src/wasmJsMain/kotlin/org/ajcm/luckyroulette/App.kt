package org.ajcm.luckyroulette

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
            Row(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                ParticipantsView(
                    modifier = Modifier
                        .fillMaxWidth(0.35f)
                        .fillMaxHeight()
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
                    }
                )

                RouletteContainerView(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    items = participants,
                    onSpinEnd = { winner ->
                        winnerParticipant = winner
                    },
                    onClear = {
                        participants.clear()
                    }
                )
            }

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
