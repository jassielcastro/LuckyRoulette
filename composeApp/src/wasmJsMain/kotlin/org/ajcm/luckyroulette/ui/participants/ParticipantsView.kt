package org.ajcm.luckyroulette.ui.participants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import luckyroulette.composeapp.generated.resources.Res
import luckyroulette.composeapp.generated.resources.team_work_bro
import org.ajcm.luckyroulette.ui.components.LuckyTextField
import org.jetbrains.compose.resources.painterResource

@Composable
fun ParticipantsView(
    modifier: Modifier,
    isCompact: Boolean = true,
    onAddParticipant: (String) -> Unit = {},
    onAddTopic: (String) -> Unit = {},
) {
    var topics by remember { mutableStateOf("") }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Add participants",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Enter name of participant to include in the roulette",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LuckyTextField(
                placeholder = "Enter participant name",
                isCompact = isCompact
            ) { participantNameText ->
                if (participantNameText.isNotBlank()) {
                    onAddParticipant(participantNameText)
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Enter Topics to talk about",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LuckyTextField(
                placeholder = "Enter Topic",
                isCompact = isCompact
            ) { topicText ->
                if (topicText.isNotBlank()) {
                    onAddTopic(topicText)
                    if (topics.isEmpty()) {
                        topics = "Options: $topicText"
                    } else {
                        topics += ", $topicText"
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = topics,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
            )

            if (!isCompact) {
                Image(
                    painter = painterResource(Res.drawable.team_work_bro),
                    contentDescription = "Participants",
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                )
            }

            Text(
                text = "Meetings are where the magic happens — we share, learn, and grow together. Your ideas could spark the next big thing!",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}
