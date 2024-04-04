package com.posite.compose1.presentation.layout.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor() : ViewModel(), MainViewModel {
    private val _count1 = mutableStateOf(0)
    override val count1: State<Int>
        get() = _count1

    private val _count2 = mutableStateOf(0)
    override val count2: State<Int>
        get() = _count2

    private val _count3 = mutableStateOf(0)
    override val count3: State<Int>
        get() = _count3

    fun onOneClick() {
        _count1.value = _count1.value + 1
    }

    fun onTwoClick() {
        _count2.value = _count2.value + 2
    }

    fun onThreeClick() {
        _count3.value = _count3.value + 3
    }

}