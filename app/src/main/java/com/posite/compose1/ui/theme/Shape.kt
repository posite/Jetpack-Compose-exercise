package com.posite.compose1.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shape = Shapes(
    extraLarge = RoundedCornerShape(90.dp),
    large = RoundedCornerShape(30.dp),
    medium = RoundedCornerShape(10.dp),
    small = RoundedCornerShape(5.dp),
    extraSmall = CircleShape
)