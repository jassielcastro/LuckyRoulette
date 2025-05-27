package org.ajcm.luckyroulette.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import luckyroulette.composeapp.generated.resources.Res
import luckyroulette.composeapp.generated.resources.ic_add
import org.jetbrains.compose.resources.painterResource

@Composable
fun LuckyTextField(
    placeholder: String,
    isCompact: Boolean,
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
                color = if (fieldText.isEmpty()) {
                    MaterialTheme.colorScheme.background
                } else {
                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f)
                },
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
                unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .fillMaxWidth(if (isCompact) 0.75f else 0.85f)
                .padding(end = 16.dp)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(if (isCompact) 0.85f else 1f)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
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

@Composable
fun TextPlaceHolder(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        fontSize = 16.sp,
        fontWeight = FontWeight.ExtraLight,
        modifier = modifier
    )
}
