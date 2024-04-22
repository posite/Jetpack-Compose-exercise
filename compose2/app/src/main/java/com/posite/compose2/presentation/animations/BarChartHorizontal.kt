package com.posite.compose2.presentation.animations

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.posite.compose2.Compose2Application.Companion.getColor
import com.posite.compose2.R
import kotlinx.coroutines.delay

@Composable
fun BarChartHorizontal() {
    val dataList = listOf(0.1f, 0.2f, 0.4f, 0.8f)
    var componentWidth by remember { mutableStateOf(0.dp) }
    val destiny = LocalDensity.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .onGloballyPositioned {
                componentWidth = with(destiny) {
                    it.size.width.toDp()
                }
            }, verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.Start
    ) {
        dataList.forEachIndexed { index, data ->
            val (width, setWidth) = remember { mutableStateOf(0.dp) }
            LaunchedEffect(key1 = true) {
                delay(1000L * index)
                setWidth(componentWidth * data)
            }
            val widthAnimation by animateDpAsState(
                targetValue = width,
                animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing),
                label = "bar"
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .width(widthAnimation)
                        .height(40.dp)
                        .background(
                            getColor(R.color.sky_blue),
                            shape = RoundedCornerShape(bottomEnd = 20.dp, topEnd = 20.dp)
                        )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "${(data * 100).toInt()}%")
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun BarChartHorizontalPreview() {
    BarChartHorizontal()
}