package dsokolov.ru.loan_calculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import dsokolov.ru.loan_calculator.ui.theme.SLIDER_THUMB_SIZE
import dsokolov.ru.loan_calculator.ui.theme.SLIDER_TRACK_HEIGHT
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanCalculatorSlider(
    sliderValue: Int,
    steps: Int,
    valueRange: ClosedFloatingPointRange<Float>,
    colorLight: Color,
    colorDeep: Color,
    onValueChanged: (Float) -> Unit,
) {
    var sliderValue by remember { mutableFloatStateOf(sliderValue.toFloat()) }
    Slider(
        modifier = Modifier.height(SLIDER_THUMB_SIZE.dp),
        value = sliderValue,
        onValueChange = {
            sliderValue = it
            onValueChanged.invoke(sliderValue)
        },
        thumb = {
            Box(
                modifier = Modifier
                    .size(SLIDER_THUMB_SIZE.dp)
                    .clip(CircleShape)
                    .drawBehind {
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(colorLight, colorDeep, colorDeep, colorDeep, colorLight)
                            )
                        )
                    },
            )
        },
        track = { sliderState ->
            val offset = SLIDER_THUMB_SIZE / 2
            val progressSize = sliderState.value - sliderState.valueRange.start
            val totalTrackSize = sliderState.valueRange.endInclusive - sliderState.valueRange.start
            val progress = progressSize / totalTrackSize

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SLIDER_TRACK_HEIGHT.dp)
            ) {
                Box(
                    modifier = Modifier
                        .layout { measurable, constraints ->
                            val pxOffset = (constraints.maxWidth * progress).toInt()
                            val desiredWidth = constraints.maxWidth - pxOffset + SLIDER_TRACK_HEIGHT.dp.roundToPx()
                            val desiredHeight = SLIDER_TRACK_HEIGHT.dp.roundToPx()

                            val placeable = measurable.measure(
                                constraints.copy(
                                    minWidth = desiredWidth,
                                    maxWidth = desiredWidth,
                                    minHeight = desiredHeight,
                                    maxHeight = desiredHeight,
                                )
                            )

                            layout(placeable.width, placeable.height) {
                                placeable.place(pxOffset, 0)
                            }
                        }
                        .background(Color.Gray, RoundedCornerShape(100))
                )
                Box(
                    modifier = Modifier
                        .layout { measurable, constraints ->
                            val progressWidth = constraints.maxWidth * progress
                            val maxOffset = offset.dp.roundToPx()
                            val edgeDelta = min(
                                (constraints.maxWidth - (progressWidth + maxOffset)).toInt(),
                                0,
                            )
                            val pxOffset = maxOffset + edgeDelta
                            val desiredWidthSuffix = maxOffset - edgeDelta
                            val desiredWidth = (progressWidth).toInt() + desiredWidthSuffix
                            val desiredHeight = SLIDER_TRACK_HEIGHT.dp.roundToPx()

                            val placeable = measurable.measure(
                                constraints.copy(
                                    minWidth = desiredWidth,
                                    maxWidth = desiredWidth,
                                    minHeight = desiredHeight,
                                    maxHeight = desiredHeight,
                                )
                            )

                            layout(placeable.width, placeable.height) {
                                placeable.place(-pxOffset, 0)
                            }
                        }
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(colorDeep, colorLight)
                            ),
                            shape = RoundedCornerShape(100)
                        )
                )
            }
        },
        steps = steps,
        valueRange = valueRange,
    )
}