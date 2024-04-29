package com.posite.compose2.presentation.ex3

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.posite.compose2.presentation.base.BaseActivity
import com.posite.compose2.presentation.ex3.vm.TimerViewModel
import com.posite.compose2.presentation.ex3.vm.TimerViewModelImpl
import com.posite.compose2.ui.theme.SkyBlue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class TimerActivity : BaseActivity() {
    @Composable
    override fun InitView() {
        TimerScreen()
    }
}

@Composable
fun TimerScreen() {
    val viewModel: TimerViewModel = hiltViewModel<TimerViewModelImpl>()
    LaunchedEffect(viewModel.currentTime.value) {
        if (viewModel.currentTime.value > 0) {
            delay(1000)
            viewModel.tickTok()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTime(viewModel)
        DrawTimer(viewModel)
    }
}

@Composable
private fun InputTime(viewModel: TimerViewModel) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    OutlinedTextField(
        value = viewModel.time.value,
        onValueChange = { viewModel.onTimeChange(it) },
        label = { Text(text = "시간 입력!") },
        placeholder = { Text(text = "시간을 입력해주세요!") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            if (viewModel.time.value.isNotEmpty() && viewModel.time.value.toInt() in 0..59) {
                focusManager.clearFocus()
                viewModel.onStartClick()
            } else {
                Toast.makeText(context, "시간을 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
        }),
        maxLines = 1
    )
    Spacer(modifier = Modifier.height(12.dp))
    Button(onClick = {
        if (viewModel.time.value.isNotEmpty() && viewModel.time.value.toInt() in 0..59) {
            focusManager.clearFocus()
            viewModel.onStartClick()
        } else {
            Toast.makeText(context, "시간을 입력해주세요!", Toast.LENGTH_SHORT).show()
        }
    }, colors = ButtonDefaults.buttonColors(containerColor = SkyBlue, contentColor = Color.White)) {
        Text(text = "시작!")
    }
}

@Composable
fun DrawTimer(viewModel: TimerViewModel) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2
        val sweepAngle = (viewModel.currentTime.value.toFloat() / 60) * 360f
        drawArc(
            color = SkyBlue,
            startAngle = -90f,
            sweepAngle = sweepAngle,
            useCenter = true,
            topLeft = Offset(center.x - radius, center.y - radius),
            size = Size(radius * 2, radius * 2),
            style = Fill
        )
        for (second in 0 until 60) {
            val angle = Math.toRadians(second.toDouble() * 6 - 90)
            val startRadius = if (second % 5 == 0) radius - 20.dp.toPx() else radius - 10.dp.toPx()
            val endRadius = radius

            val startX = center.x + startRadius * Math.cos(angle).toFloat()
            val endX = center.x + endRadius * Math.cos(angle).toFloat()

            val startY = center.y + startRadius * Math.sin(angle).toFloat()
            val endY = center.y + endRadius * Math.sin(angle).toFloat()

            drawLine(
                color = Color.Black,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = if (second % 5 == 0) 4.dp.toPx() else 2.dp.toPx()
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun TimerScreenPreview() {
    TimerScreen()
}