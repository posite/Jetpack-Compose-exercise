package com.posite.compose2.presentation.ex2

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.posite.compose2.presentation.base.BaseActivity
import com.posite.compose2.presentation.ex2.model.TodoItem
import com.posite.compose2.presentation.ex2.vm.CalendarViewModel
import com.posite.compose2.presentation.ex2.vm.CalendarViewModelImpl
import com.posite.compose2.ui.theme.SkyBlue
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class CalendarActivity : BaseActivity() {
    @Composable
    override fun InitView() {
        CalendarScreen()
    }
}

private val MONTH_FORMMATTER = SimpleDateFormat("yyyy년 M월", Locale.KOREA)
private val FULL_FORMATTER = SimpleDateFormat("yyyy년 M월 d일", Locale.KOREA)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen() {
    val viewModel: CalendarViewModel = hiltViewModel<CalendarViewModelImpl>()
    Scaffold(modifier = Modifier
        .fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = { Text(MONTH_FORMMATTER.format(viewModel.calendar.value.time)) },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Setting"
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notification"
                    )
                }
            }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)

        ) {
            TodoList()
        }

    }
}

@Composable
fun CalendarDaysOfWeek() {
    val daysList = listOf("일", "월", "화", "수", "목", "금", "토")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        daysList.forEach {
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = it, fontSize = 18.sp, color = when (it) {
                        "일" -> Color.Red
                        "토" -> Color.Blue
                        else -> Color.Black
                    }
                )
            }

        }
    }
}

@Composable
fun CalendarDaysOfMonth(todo: List<TodoItem>) {
    val viewModel: CalendarViewModel = hiltViewModel<CalendarViewModelImpl>()
    val date = viewModel.calendar.value
    date.set(Calendar.DAY_OF_MONTH, 1)
    val thisMonthDayMax = date.getActualMaximum(Calendar.DAY_OF_MONTH) // 현재 달의 max
    val thisMonthFirstDay = date.get(Calendar.DAY_OF_WEEK) - 1 // 1일이 무슨 요일인지
    val thisMonthWeeksCount = (thisMonthDayMax + thisMonthFirstDay + 6) / 7 // 현재 달의 week 갯수

    Column() {

        repeat(thisMonthWeeksCount) { week ->

            Row() {

                repeat(7) { day ->

                    val resultDay = week * 7 + day - thisMonthFirstDay + 1
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                            .border(1.dp, Color.Gray),
                        contentAlignment = Alignment.Center
                    ) {
                        val color =
                            if (day == 0) Color.Red else if (day == 6) Color.Blue else Color.Black

                        if (resultDay in 1..thisMonthDayMax) {
                            Column(modifier = Modifier
                                .fillMaxSize(),
                                verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(modifier = Modifier.fillMaxWidth().background(SkyBlue), contentAlignment = Alignment.Center) {
                                    Text(todo[resultDay-1].title, fontSize = 12.sp)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = resultDay.toString(),
                                    fontSize = 16.sp,
                                    color = color
                                )
                            }
                        } else {
                            val remainDay = if (resultDay < 1) {
                                val time = date.time
                                val prevCalendar = Calendar.getInstance()
                                prevCalendar.time = time
                                prevCalendar.add(Calendar.MONTH, -1)
                                val prev = prevCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
                                prev + resultDay
                            } else {
                                resultDay - thisMonthDayMax
                            }
                            Text(
                                text = remainDay.toString(),
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }


                    }

                }

            }

        }

    }
}

@Composable
fun TodoList() {
    val viewModel: CalendarViewModel = hiltViewModel<CalendarViewModelImpl>()
    val date = viewModel.calendar.value
    val thisMonthDayMax = date.getActualMaximum(Calendar.DAY_OF_MONTH) // 현재 달의 max
    val todoList = mutableListOf<TodoItem>()
    repeat(thisMonthDayMax) { day ->
        todoList.add(TodoItem(FULL_FORMATTER.format(date), "할일 ${day + 1}", "상세 내용 ${day + 1}"))
    }

    val lazyColumnState = rememberLazyListState()
    val lazyRowState = rememberLazyListState()
    val isScrolled = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        snapshotFlow { lazyColumnState.isScrollInProgress }.collect {
            isScrolled.value = it
        }

    }

    LaunchedEffect(lazyColumnState.firstVisibleItemIndex) {
        lazyRowState.scrollToItem(lazyColumnState.firstVisibleItemIndex)
    }

    if (isScrolled.value.not()) {
        CalendarDaysOfWeek()
        Spacer(modifier = Modifier.padding(8.dp))
        CalendarDaysOfMonth(todoList)
        Spacer(modifier = Modifier.padding(8.dp))
    } else {
        LazyRow(state = lazyRowState) {
            items(todoList.size) { day ->
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val calendar = Calendar.getInstance()
                    calendar.set(Calendar.DAY_OF_MONTH, day + 1)
                    val color = if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        Color.Red
                    } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                        Color.Blue
                    } else {
                        Color.Black
                    }

                    Text(
                        text = (day + 1).toString(),
                        fontSize = 16.sp,
                        color = color
                    )
                }
            }
        }
    }


    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp), state = lazyColumnState) {
//        item {
//            CalendarDaysOfWeek()
//            Spacer(modifier = Modifier.padding(8.dp))
//            CalendarDaysOfMonth()
//            Spacer(modifier = Modifier.padding(8.dp))
//        }
        todoList.forEach {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = it.title, fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = it.date, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = it.content, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun CalendarScreenPreview() {
    CalendarScreen()
}