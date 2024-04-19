package com.posite.compose1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.posite.compose1.R

// Set new font
val meetme = FontFamily(Font(R.font.meetme))

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = meetme,
        fontWeight = FontWeight.Bold,
        fontSize = 120.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = meetme,
        fontWeight = FontWeight.Normal,
        fontSize = 80.sp
    ),
    labelLarge = TextStyle(
        fontFamily = meetme,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    titleSmall = TextStyle(
        fontFamily = meetme,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = meetme,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = meetme,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
)