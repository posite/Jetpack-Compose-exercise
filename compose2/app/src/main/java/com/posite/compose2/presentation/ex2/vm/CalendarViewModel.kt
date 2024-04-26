package com.posite.compose2.presentation.ex2.vm

import android.icu.util.Calendar
import androidx.compose.runtime.State

interface CalendarViewModel {
    val calendar: State<Calendar>
}