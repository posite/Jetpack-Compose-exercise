package com.posite.compose2.presentation.ex3.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimerViewModelImpl @Inject constructor() : TimerViewModel, ViewModel() {
    private val _time: MutableState<String> = mutableStateOf("")
    override val time: State<String>
        get() = _time

    private val _currentTime: MutableState<Int> = mutableStateOf(0)
    override val currentTime: State<Int>
        get() = _currentTime

    override fun onTimeChange(time: String) {
        _time.value = time
    }

    override fun onStartClick() {
        _currentTime.value = _time.value.toInt()
    }

    override fun tickTok() {
        _currentTime.value = _currentTime.value - 1
    }


}