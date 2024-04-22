package com.posite.compose1.presentation.ex4

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.posite.compose1.ComposeApplication.Companion.getString
import com.posite.compose1.R
import com.posite.compose1.presentation.base.BaseActivity
import com.posite.compose1.presentation.ex4.vm.CalendarViewModel
import com.posite.compose1.presentation.ex4.vm.CalendarViewModelImpl
import com.posite.compose1.ui.theme.Compose1Theme
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class Ex4Activity : BaseActivity() {
    private val viewModel: CalendarViewModel by viewModels<CalendarViewModelImpl>()

    @Composable
    override fun InitView() {
        DrawBackground()
    }

    @Composable
    fun DrawBackground() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CalendarHeaderTitle()
            Spacer(modifier = Modifier.padding(4.dp))
            CalendarHeaderButton()
            Spacer(modifier = Modifier.padding(12.dp))
            CalendarDayOfWeek()
            Spacer(modifier = Modifier.padding(8.dp))
            CalendarDay()
        }
    }

    @Composable
    fun CalendarHeaderTitle() {
        Text(
            text = DATE_FORMATTER.format(viewModel.time.value.time),
            fontSize = 24.sp
        )

    }

    @Composable
    fun CalendarHeaderButton() {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    val calendar = viewModel.time.value
                    calendar.add(Calendar.MONTH, -1)
                    viewModel.setTime(calendar)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "뒤로 가기",
                    modifier = Modifier.size(32.dp)
                )
            }
            Button(
                onClick = {
                    val calendar = viewModel.time.value
                    calendar.add(Calendar.MONTH, 1)
                    viewModel.setTime(calendar)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "앞으로 가기",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }

    @Composable
    fun CalendarDayOfWeek() {
        val dayOfWeeks = listOf("일", "월", "화", "수", "목", "금", "토")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            dayOfWeeks.forEach {
                Box {
                    Text(
                        text = it, color = when (it) {
                            "일" -> {
                                Color.Red
                            }

                            "토" -> {
                                Color.Blue
                            }

                            else -> {
                                Color.Black

                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun CalendarDay() {
        val calendar = viewModel.time.value
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val monthFirstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
        val weekCount = (maxDay + monthFirstDayOfWeek + 6) / 7

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            repeat(weekCount) { week ->
                Row {
                    repeat(7) { day ->
                        val dayNumber = week * 7 + day - monthFirstDayOfWeek + 1
                        if (dayNumber in 1..maxDay) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = dayNumber.toString())
                            }
                        } else {
                            val remainDay = if (dayNumber < 1) {
                                val time = calendar.time
                                val prevCalendar = Calendar.getInstance()
                                prevCalendar.time = time
                                prevCalendar.add(Calendar.MONTH, -1)
                                val prev = prevCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
                                prev + dayNumber
                            } else {
                                dayNumber - maxDay
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = remainDay.toString(), color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }


    @Composable
    @Preview(showBackground = true)
    fun CalendarPreview() {
        Compose1Theme {
            DrawBackground()
        }
    }

    companion object {
        private val DATE_FORMATTER =
            SimpleDateFormat(getString(R.string.calendar_format), Locale.KOREA)
    }
}