package com.posite.compose2.presentation.ex1.model

import androidx.compose.ui.graphics.Color
import com.guru.fontawesomecomposelib.FaIconType

data class ConsumeItem(
    val icon: FaIconType,
    val color: Color,
    val content: String,
    val amount: Long,
    val fraction: Float
)
