package org.ajcm.luckyroulette.ui.roulette

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import kotlinx.coroutines.launch
import org.ajcm.luckyroulette.ui.models.RouletteItem
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RouletteSpinnerView(
    items: List<RouletteItem>,
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    onSpinEnd: (RouletteItem) -> Unit = {}
) {
    val anglePerItem = 360f / items.size
    val rotation = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    val textMeasurer = rememberTextMeasurer()

    BoxWithConstraints(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    coroutineScope.launch {
                        val targetRotation = (360 * 5) + (0..360).random()
                        rotation.snapTo(0f)
                        rotation.animateTo(
                            targetValue = targetRotation.toFloat(),
                            animationSpec = tween(
                                durationMillis = 3000,
                                easing = FastOutSlowInEasing
                            )
                        )
                        val finalAngle = rotation.value % 360
                        val pointerAngle = 90f
                        val relativeAngle = (360 - (finalAngle - pointerAngle + 360) % 360) % 360
                        val selectedIndex = (relativeAngle / anglePerItem).toInt() % items.size
                        onSpinEnd(items[selectedIndex])
                    }
                })
            }
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        val rouletteSize by remember(screenWidth, screenHeight) {
            derivedStateOf {
                min(screenWidth, screenHeight)
            }
        }

        Canvas(
            modifier = Modifier.
            size(rouletteSize)
                .align(Alignment.Center),
        ) {
            val radius = size.minDimension / 2
            var startAngle = 0f

            items.forEachIndexed { index, item ->
                val sweepAngle = anglePerItem
                drawIntoCanvas { canvas ->
                    rotate(rotation.value, Offset(radius, radius)) {

                        drawArc(
                            color = Color(0xFF1b1b1b),
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = true,
                            topLeft = Offset.Zero,
                            size = size
                        )

                        drawArc(
                            color = item.color,
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = true,
                            topLeft = Offset.Zero,
                            size = size.copy(
                                width = size.width - 3,
                                height = size.height - 3
                            )
                        )

                        val middleAngle = startAngle + sweepAngle / 2
                        val angleInRadians = middleAngle.toDouble().toRadians() + 25

                        val textRadius = radius * 0.85f
                        val textCenter = Offset(
                            x = radius + cos(angleInRadians).toFloat() * textRadius,
                            y = radius + sin(angleInRadians).toFloat() * textRadius
                        )
                        withTransform({
                            rotate(degrees = middleAngle + 90f, pivot = textCenter)
                        }) {
                            drawText(
                                textMeasurer = textMeasurer,
                                text = AnnotatedString(item.value),
                                topLeft = textCenter,
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = fontSize,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }
                startAngle += sweepAngle
            }

            drawIntoCanvas {
                it.drawCircle(
                    center = Offset(radius, radius),
                    radius = radius * 0.33f,
                    paint = Paint().apply {
                        style = PaintingStyle.Fill
                        color = Color(0xFF1b1b1b)
                    }
                )

                it.drawCircle(
                    center = Offset(radius, radius),
                    radius = radius * 0.3f,
                    paint = Paint().apply {
                        style = PaintingStyle.Stroke
                        color = Color(0xFF131314)
                        strokeWidth = 10.dp.toPx()
                    }
                )
            }
        }
    }
}

fun Double.toRadians(): Double = this * (PI / 180.0)
