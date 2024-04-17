package com.posite.compose1.presentation.ex3.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.posite.compose1.R

@Composable
fun ProgressGraph() {
    DrawGraph(40f, 100f)
}

@Composable
fun DrawGraph(percent: Float, maxPercent: Float) {
    val currentProgress = 360 * (percent / maxPercent)
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(24.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 28f)
            )
            drawArc(
                color = Color(context.getColor(R.color.sky_blue)),
                startAngle = -90f,
                sweepAngle = currentProgress,
                useCenter = false,
                style = Stroke(width = 28f)
            )
        }

        Text(text ="${((percent / maxPercent)*100).toInt()}%", modifier = Modifier.align(Alignment.Center), fontSize = 32.sp)
    }

}


@Composable
@Preview(showBackground = true)
fun ProgressGraphPreview() {
    ProgressGraph()

}