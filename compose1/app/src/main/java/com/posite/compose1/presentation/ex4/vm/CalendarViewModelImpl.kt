package com.posite.compose1.presentation.ex4.vm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CalendarViewModelImpl @Inject constructor() : ViewModel(), CalendarViewModel {
    private val _time = mutableStateOf(Calendar.getInstance())
    override val time: State<Calendar>
        get() = _time

    override fun setTime(time: Calendar) {
        _time.value = null
        _time.value = time

        Log.d("calendar", _time.value.time.toString())
    }
}