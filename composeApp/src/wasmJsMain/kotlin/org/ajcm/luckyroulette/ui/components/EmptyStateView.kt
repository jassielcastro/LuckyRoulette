package org.ajcm.luckyroulette.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import luckyroulette.composeapp.generated.resources.Res
import luckyroulette.composeapp.generated.resources.questions_bro
import org.jetbrains.compose.resources.painterResource

@Composable
fun LuckyEmptyStateView(
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(Res.drawable.questions_bro),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f),
        )

        Text(
            text = "No participants added yet...",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .weight(0.3f),
        )
    }
}
