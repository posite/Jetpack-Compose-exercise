package com.posite.compose1.presentation.layout.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor() : ViewModel(), MainViewModel {
    private val _count = mutableStateOf(0)
    override val count: State<Int> = _count

    fun onClick() {
        _count.value = _count.value + 1
    }
}