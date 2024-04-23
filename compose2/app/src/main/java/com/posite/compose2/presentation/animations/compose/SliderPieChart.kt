package com.posite.compose2.presentation.animations.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.posite.compose2.Compose2Application.Companion.getColor
import com.posite.compose2.R

@Composable
fun SliderPieChart() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {

        val (sliderProgress, setProgress) = remember {
            mutableStateOf(0f)
        }

        Slider(
            value = sliderProgress,
            onValueChange = { setProgress(it) },
            colors = SliderDefaults.colors(
                thumbColor = getColor(R.color.sky_blue),
                activeTrackColor = getColor(R.color.teal_200),
                inactiveTrackColor = Color.LightGray,
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent
            ),
            steps = 100
        )

        Spacer(modifier = Modifier.padding(20.dp))
        DrawPieChart(sliderProgress * 100f)
    }
}

@Composable
fun DrawPieChart(progress: Float) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(350.dp)
        .padding(20.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 70f)
            )
            drawArc(
                color = getColor(R.color.sky_blue),
                startAngle = -90f,
                sweepAngle = progress * 3.6f,
                useCenter = false,
                style = Stroke(width = 70f)
            )
        }

        Text(text = "${progress.toInt()}%", fontSize = 40.sp)
    }
}

@Composable
@Preview(showBackground = true)
fun ProgressPieChartPreview() {
    DrawPieChart(50f)
}