package org.ajcm.luckyroulette.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import luckyroulette.composeapp.generated.resources.Res
import luckyroulette.composeapp.generated.resources.ic_add
import org.ajcm.luckyroulette.ui.participants.TextPlaceHolder
import org.jetbrains.compose.resources.painterResource

@Composable
fun LuckyTextField(
    placeholder: String,
    onAddParticipant: (String) -> Unit
) {
    var fieldText by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        OutlinedTextField(
            value = fieldText,
            onValueChange = {
                fieldText = it
            },
            placeholder = {
                TextPlaceHolder(
                    text = placeholder,
                )
            },
            textStyle = TextStyle(
                color = if (fieldText.isEmpty()) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            ),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Go,
            ),
            keyboardActions = KeyboardActions {
                if (fieldText.isBlank()) return@KeyboardActions
                onAddParticipant(fieldText)
                fieldText = ""
            },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(end = 16.dp)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
            onClick = {
                if (fieldText.isBlank()) return@Button
                onAddParticipant(fieldText)
                fieldText = ""
            }
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_add),
                contentDescription = "Add",
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.onPrimary,
                ),
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}
