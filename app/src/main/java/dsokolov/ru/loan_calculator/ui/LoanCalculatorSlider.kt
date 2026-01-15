package dsokolov.ru.loan_calculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dsokolov.ru.loan_calculator.ui.theme.SLIDER_THUMB_SIZE
import dsokolov.ru.loan_calculator.ui.theme.SLIDER_TRACK_WIDTH

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
            val connectionOffset = SLIDER_THUMB_SIZE * 0.25f
            val connectionSize = SLIDER_THUMB_SIZE * 0.6f
            Box(
                Modifier.size(SLIDER_THUMB_SIZE.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Box(
                    modifier = Modifier
                        .width(connectionSize.dp)
                        .height(connectionSize.dp)
                        .offset(-(connectionOffset).dp)
                        .clip(CircleShape)
                        .drawBehind {
                            drawCircle(
                                brush = Brush.radialGradient(
                                    colors = listOf(colorLight, colorLight)
                                )
                            )
                        },
                )
            }
            Box(
                Modifier
                    .size(SLIDER_THUMB_SIZE.dp)
                    .clip(CircleShape)
                    .drawBehind {
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(colorLight, colorDeep, colorDeep, colorLight)
                            )
                        )
                    },
            )
        },
        track = { sliderState ->
            val offset = SLIDER_THUMB_SIZE / 2
            val progressSize = sliderState.value - sliderState.valueRange.start
            val trackSize = sliderState.valueRange.endInclusive - sliderState.valueRange.start
            val progress = progressSize / trackSize

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(offset.dp)
                    .height(SLIDER_TRACK_WIDTH.dp)
                    .background(Color.Gray, RoundedCornerShape(100))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .offset(-(offset * 2).dp)
                        .height(SLIDER_TRACK_WIDTH.dp)
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