package com.posite.compose1.presentation.ex2

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.posite.compose1.R
import com.posite.compose1.presentation.base.BaseActivity
import com.posite.compose1.ui.theme.Compose1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Ex2Activity : BaseActivity() {
    @Composable
    override fun InitView() {
        DrawBackground()
    }

    @Composable
    fun DrawBackground() {
        val (dice, rollDice) = remember { mutableStateOf(1) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(getColor(R.color.sky_blue))),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .border(2.dp, Color.Black)
                    .background(color = Color.LightGray)
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                DrawDice(dice)
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {
                rollDice((1..6).random())
            }, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
                Text(
                    text = "Roll Dice",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = "Dice: $dice",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun DrawDice(number: Int) {
        when (number) {
            1 -> Draw1()
            2 -> Draw2()
            3 -> Draw3()
            4 -> Draw4()
            5 -> Draw5()
            6 -> Draw6()
        }
    }

    @Composable
    fun Draw1() {
        Box(modifier = Modifier.size(40.dp)) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color.Black,
                    radius = size.minDimension / 10
                )
            }
        }
    }

    @Composable
    fun Draw2() {
        Row {
            Draw1()
            Spacer(modifier = Modifier.size(40.dp))
            Draw1()
        }
    }

    @Composable
    fun Draw3() {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Draw1()
            }
            Spacer(modifier = Modifier.size(20.dp))
            Draw1()
            Spacer(modifier = Modifier.size(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Draw1()
            }
        }
    }

    @Composable
    fun Draw4() {
        Column {
            Row {
                Draw1()
                Spacer(modifier = Modifier.size(40.dp))
                Draw1()
            }
            Spacer(modifier = Modifier.size(20.dp))
            Row {
                Draw1()
                Spacer(modifier = Modifier.size(40.dp))
                Draw1()
            }
        }
    }

    @Composable
    fun Draw5() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Draw1()
                Spacer(modifier = Modifier.size(40.dp))
                Draw1()
            }
            Spacer(modifier = Modifier.size(20.dp))
            Draw1()
            Spacer(modifier = Modifier.size(20.dp))
            Row {
                Draw1()
                Spacer(modifier = Modifier.size(40.dp))
                Draw1()
            }
        }
    }

    @Composable
    fun Draw6() {
        Column {
            Row {
                Draw1()
                Spacer(modifier = Modifier.size(40.dp))
                Draw1()
            }
            Spacer(modifier = Modifier.size(20.dp))
            Row {
                Draw1()
                Spacer(modifier = Modifier.size(40.dp))
                Draw1()
            }
            Spacer(modifier = Modifier.size(20.dp))
            Row {
                Draw1()
                Spacer(modifier = Modifier.size(40.dp))
                Draw1()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Compose1Theme {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                //Draw1()
                //Draw2()
                //Draw3()
                //Draw4()
                //Draw5()
                //Draw6()
            }
        }
    }
}