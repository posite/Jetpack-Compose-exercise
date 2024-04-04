package com.posite.compose1.presentation.layout.vm

import androidx.compose.runtime.State

interface MainViewModel {
    val count1: State<Int>
    val count2: State<Int>
    val count3: State<Int>
}