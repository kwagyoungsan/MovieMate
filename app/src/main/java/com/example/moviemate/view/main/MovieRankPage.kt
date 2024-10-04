package com.example.moviemate.view.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviemate.R

@Composable
fun MovieRankPage() {
    var isFirstButtonChecked by remember { mutableStateOf(true) }
    var isSecondButtonChecked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopStart) // 좌측 상단으로 정렬
                .padding(16.dp), // 패딩 추가
            horizontalArrangement = Arrangement.spacedBy(8.dp) // 버튼 간의 간격 조정
        ) {
            Spacer(modifier = Modifier.height(16.dp)) // 텍스트와 스위치 간의 간격

            // 첫 번째 토글 버튼
            ToggleButtonCompose(
                checked = isFirstButtonChecked,
                onCheckedChange = {
                    isFirstButtonChecked = it
                    if (it) {
                        isSecondButtonChecked = false // 첫 번째 버튼이 체크되면 두 번째 버튼 해제
                    }
                },
                text = "일간"
            )

            Spacer(modifier = Modifier.height(16.dp)) // 버튼 간의 간격

            // 두 번째 토글 버튼
            ToggleButtonCompose(
                checked = isSecondButtonChecked,
                onCheckedChange = {
                    isSecondButtonChecked = it
                    if (it) {
                        isFirstButtonChecked = false // 두 번째 버튼이 체크되면 첫 번째 버튼 해제
                    }
                },
                text = "주간"
            )
        }
    }
}

@Composable
fun ToggleButtonCompose(checked: Boolean, onCheckedChange: (Boolean) -> Unit, text: String) {
    val tint by animateColorAsState(if (checked) Green else LightGray)
    val textColor = if (checked) Black else White

    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = Modifier
            .clip(CircleShape)
            .border(1.dp, Transparent, CircleShape)
            .background(tint)
    ) {
        Text(text, color = textColor)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMovieRankPage() {
    MovieRankPage()
}