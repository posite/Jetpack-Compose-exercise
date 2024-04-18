package com.posite.compose1.presentation.ex4.vm

import androidx.compose.runtime.State
import java.util.Calendar

interface CalendarViewModel {
    val time: State<Calendar>

    fun setTime(time: Calendar)
}