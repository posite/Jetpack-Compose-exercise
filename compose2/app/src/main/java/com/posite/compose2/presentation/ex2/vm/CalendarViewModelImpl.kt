package com.posite.compose2.presentation.ex2.vm

import android.icu.util.Calendar
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarViewModelImpl @Inject constructor() : CalendarViewModel, ViewModel() {
    private val _calendar = mutableStateOf(Calendar.getInstance())
    override val calendar: State<Calendar>
        get() = _calendar
}