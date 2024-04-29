package com.posite.compose2.presentation.ex3.vm

import androidx.compose.runtime.State

interface TimerViewModel {
    val time: State<String>
    val currentTime: State<Int>

    fun onTimeChange(time: String)
    fun onStartClick()
    fun tickTok()
}