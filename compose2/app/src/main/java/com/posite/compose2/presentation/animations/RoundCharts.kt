package com.posite.compose2.presentation.animations

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoundCharts() {
    val list = listOf(
        ChartData(Color.Black, 0.5f),
        ChartData(Color.Magenta, 0.2f),
        ChartData(Color.Blue, 0.3f)
    )
    var start = 0f
    val (progress, setProgress) = remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(Unit) {
        setProgress(1f)
    }
    val animationState by animateFloatAsState(
        targetValue = progress,
        label = "",
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(400.dp)
                .padding(20.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                list.forEach { data ->
                    //도넛
                    /*
                    drawArc(
                        color = data.color,
                        startAngle = start,
                        sweepAngle = data.fraction * animationState * 360f,
                        useCenter = false,
                        style = Stroke(width = 100f)
                    )
                    */
                    //Pie
                    drawArc(
                        color = data.color,
                        startAngle = start,
                        sweepAngle = data.fraction * animationState * 360f,
                        useCenter = true,
                        style = Fill
                    )
                    val paint = Paint().asFrameworkPaint().apply {
                        color = when(data.color) {
                            Color.Black -> android.graphics.Color.WHITE
                            else -> android.graphics.Color.BLACK
                        }
                        textSize = 60f
                    }

                    val midPosition = start + data.fraction * 180f // 중앙에 위치
                    val radiusPosition = size.width * 0.5f * 0.5f // 원 중앙에서 얼마나 떨어지는지

                    // x y 좌표 계산
                    val xPosition =
                        (radiusPosition * kotlin.math.cos(midPosition * (Math.PI / 180))).toFloat() + size.width * 0.5f // x 좌표
                    val yPosition =
                        (radiusPosition * kotlin.math.sin(midPosition * (Math.PI / 180))).toFloat() + size.height * 0.5f // y 좌표

                    // 텍스트의 너비 및 높이를 계산
                    val textWidth = paint.measureText(data.fraction.toString()) // 텍스트 가로 넓이
                    val textHeight = paint.descent() - paint.ascent() // 텍스트 세로 넓이

                    val xPositionChanged = xPosition - textWidth * 0.5f // 텍스트 가로 넓이 절반
                    val yPositionChanged = yPosition + textHeight * 0.5f // 텍스트 세로 넓이 절반


                    drawIntoCanvas {
                        it.nativeCanvas.drawText(
                            "${(data.fraction * 100).toInt()}%",
                            xPositionChanged,
                            yPositionChanged,
                            paint
                        )
                    }

                    start += data.fraction * 360f
                }
            }
        }
    }
}

data class ChartData(val color: Color, val fraction: Float)

@Composable
@Preview(showBackground = true)
fun RoundChartsPreview() {
    RoundCharts()
}