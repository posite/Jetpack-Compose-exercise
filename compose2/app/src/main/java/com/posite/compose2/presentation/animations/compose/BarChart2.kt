package com.posite.compose2.presentation.animations.compose

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.posite.compose2.Compose2Application
import com.posite.compose2.R
import kotlinx.coroutines.delay

@Composable
fun BarChart2() {
    val dataList = listOf(0.1f, 0.2f, 0.4f, 0.8f, 1f)
    var componentHeight by remember { mutableStateOf(0.dp) }
    val destiny = LocalDensity.current
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .onGloballyPositioned {
                componentHeight = with(destiny) {
                    it.size.height.toDp()
                }
            }, horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.Bottom
    ) {
        dataList.forEachIndexed { index, data ->
            val (height, setHeight) = remember { mutableStateOf(0.dp) }
            LaunchedEffect(key1 = true) {
                delay(1000L * index)
                setHeight((componentHeight * data))
            }
            val heightAnimation by animateDpAsState(
                targetValue = height,
                animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing),
                label = "bar"
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${(data * 100).toInt()}%")
                Box(
                    modifier = Modifier
                        .height(heightAnimation)
                        .width(40.dp)
                        .background(
                            Compose2Application.getColor(R.color.sky_blue),
                            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        )
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun BarChart2Preview() {
    BarChart1()
}