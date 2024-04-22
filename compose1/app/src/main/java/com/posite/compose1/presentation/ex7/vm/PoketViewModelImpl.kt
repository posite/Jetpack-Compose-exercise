package com.posite.compose1.presentation.ex7.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PoketViewModelImpl @Inject constructor() : ViewModel(), PoketViewModel {
    private val _poketState = mutableStateOf(true)
    override val poketState: State<Boolean>
        get() = _poketState

    override fun onPoketClick() {
        _poketState.value = _poketState.value.not()
    }
}