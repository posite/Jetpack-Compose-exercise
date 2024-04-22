package com.posite.compose1.presentation.ex3.graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.posite.compose1.R

@Composable
fun BarChart() {
    DrawBarGraph()
}

@Composable
fun DrawBarGraph() {
    val numbers = listOf(20, 6, 8, 24, 16, 10)

    val max = numbers.max()

    Column {
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            numbers.forEach {
                DrawBar(value = it, maxValue = max)
            }
        }
    }
}

@Composable
fun DrawBar(value: Int, maxValue: Int) {
    val context = LocalContext.current
    val height = (value.toFloat() / maxValue.toFloat()) * 200

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier
            .height(height.dp)
            .width(30.dp)
            .background(Color(context.getColor(R.color.sky_blue))) )

        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = value.toString(),
            fontSize = 18.sp
        )
    }

}

@Composable
@Preview(showBackground = true)
fun BarChartPreview() {
    BarChart()

}