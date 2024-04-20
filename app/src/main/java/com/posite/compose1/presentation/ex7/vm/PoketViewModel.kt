package com.posite.compose1.presentation.ex7.vm

import androidx.compose.runtime.State

interface PoketViewModel {
    val poketState: State<Boolean>

    fun onPoketClick()
}