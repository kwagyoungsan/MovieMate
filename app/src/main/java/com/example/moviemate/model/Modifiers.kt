package com.example.moviemate.model

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.customButtonModifier(isSelected: Boolean): Modifier {
    return this
        .clip(CircleShape)
        .border(1.dp, Color.Transparent, CircleShape)
        .background(if (isSelected) Color.Green else Color.LightGray)
        .width(100.dp)
        .height(40.dp)
}