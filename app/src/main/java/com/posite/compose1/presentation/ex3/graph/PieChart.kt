package com.posite.compose1.presentation.ex3.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.posite.compose1.R

@Composable
fun PieChart() {
    DrawPieGraph()
}

@Composable
fun DrawPieGraph() {
    val context = LocalContext.current
    val pie1 = 72f
    val pie2 = 28f
    val total = pie1 + pie2
    val colors = listOf(
        Color(context.getColor(R.color.sky_blue)),
        Color.Magenta
    )

    BoxWithConstraints(modifier = Modifier.padding(20.dp)) {
        val pieSize = constraints.maxWidth.toFloat()

        Canvas(modifier = Modifier.size(pieSize.dp)) {
            val sweep1 = 360 * (pie1 / total)
            val sweep2 = 360 * (pie2 / total)
            drawArc(
                color = colors[0],
                startAngle = 90f,
                sweepAngle = sweep1,
                useCenter = true,
                size = Size(pieSize, pieSize)
            )
            drawArc(
                color = colors[1],
                startAngle = 90f + sweep1,
                sweepAngle = sweep2,
                useCenter = true,
                size = Size(pieSize, pieSize)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PieChartPreView() {
    PieChart()

}