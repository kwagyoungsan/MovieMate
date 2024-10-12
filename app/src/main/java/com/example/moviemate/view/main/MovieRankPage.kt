package com.example.moviemate.view.main

import android.content.Context
import android.widget.DatePicker
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviemate.R
import java.time.LocalDate
import java.util.Calendar

@Composable
fun MovieRankPage() {
    var isFirstButtonChecked by remember { mutableStateOf(true) }
    var isSecondButtonChecked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .background(Red)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp), // 버튼 간의 간격 조정
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(16)

                Text(text = "기간")
                // 첫 번째 토글 버튼
                ToggleButtonCompose(
                    checked = isFirstButtonChecked,
                    onCheckedChange = {
                        isFirstButtonChecked = it
                        if (it) {
                            isSecondButtonChecked = false // 첫 번째 버튼이 체크되면 두 번째 버튼 해제
                        } else {
                            isSecondButtonChecked = true
                        }
                    },
                    text = "일간"
                )

                Spacer(16)

                // 두 번째 토글 버튼
                ToggleButtonCompose(
                    checked = isSecondButtonChecked,
                    onCheckedChange = {
                        isSecondButtonChecked = it
                        if (it) {
                            isFirstButtonChecked = false // 두 번째 버튼이 체크되면 첫 번째 버튼 해제
                        } else {
                            isFirstButtonChecked = true
                        }
                    },
                    text = "주간"
                )
            }
            Row (
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp), // 버튼 간의 간격 조정
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(16)
                Text(text = "날짜")
                Spacer(16)
                DatePicker(context = LocalContext.current)

            }
            Row (
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp), // 버튼 간의 간격 조정
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(16)
                Text(text = "관객 수")
                Spacer(16)
                Text(text = "관객 수 2")

            }
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

@Composable
fun Spacer(num: Int) {
    Spacer(modifier = Modifier.height(num.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(context: Context) {
    var selectedDate by remember { mutableStateOf("") } // 초기값을 빈 문자열로 설정
    var showDatePickerDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 날짜 선택 버튼
        Button(onClick = { showDatePickerDialog = true }) {
            Text(text = if (selectedDate.isEmpty()) "날짜 선택" else selectedDate) // 선택된 날짜가 없으면 "날짜 선택" 표시
        }

        // 날짜 선택 다이얼로그 표시
        if (showDatePickerDialog) {
            // 현재 날짜 가져오기
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // DatePickerDialog 생성
            val datePickerDialog = android.app.DatePickerDialog(
                context,
                { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                    // 사용자가 선택한 날짜를 업데이트
                    selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    showDatePickerDialog = false // 다이얼로그 닫기
                },
                year, month, day
            )
            datePickerDialog.show()
            showDatePickerDialog = false // 다이얼로그가 열리면 상태 업데이트
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieRankPage() {
    MovieRankPage()
}