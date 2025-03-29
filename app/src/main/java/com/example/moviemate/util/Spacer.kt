package com.example.moviemate.util

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Spacer(num: Int) {
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(num.dp))
}