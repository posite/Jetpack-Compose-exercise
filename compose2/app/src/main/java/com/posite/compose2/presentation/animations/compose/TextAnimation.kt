package com.posite.compose2.presentation.animations.compose

import android.icu.text.NumberFormat
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextAnimation() {
    val (resultMoney, setMoney) = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val moneyAnimation by animateIntAsState(
            targetValue = resultMoney,
            label = "",
            animationSpec = tween(500)
        )
        val formattedMoney = NumberFormat.getNumberInstance().format(moneyAnimation)
        Text(
            text = "$formattedMoney 원",
            fontSize = 60.sp
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.width(100.dp), onClick = { setMoney(resultMoney + 10000) }) {
                Text(text = "UP!")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                modifier = Modifier.width(100.dp),
                onClick = { if (resultMoney > 0) setMoney(resultMoney - 10000) }) {
                Text(text = "DOWN!")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TextAnimationPreview() {
    TextAnimation()
}