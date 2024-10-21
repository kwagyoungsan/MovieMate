package com.example.moviemate.view.main

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.layout.width
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
            .background(Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .padding(16.dp)  // 전체 Column의 padding 추가
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "기간")

                Spacer(Modifier.width(30.dp))

                // 첫 번째 토글 버튼 (일간)
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

                // 두 번째 토글 버튼 (주간)
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

            // "날짜" 텍스트와 날짜 선택 버튼 사이에 같은 간격 추가
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "날짜")
                Spacer(Modifier.width(16.dp))  // 날짜 텍스트와 날짜 선택 버튼 사이에 동일한 간격 추가

                if (isFirstButtonChecked) {
                    // 일간 선택일 경우 단일 날짜 선택
                    SingleDatePicker(context = LocalContext.current)
                } else if (isSecondButtonChecked) {
                    // 주간 선택일 경우 주간 날짜 선택
                    DateRangePickerWithAutoWeek(context = LocalContext.current)
                }
            }

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "관객 수")
                Spacer(Modifier.width(16.dp))
                Text(text = "관객 수 2")
            }

            // "검색" 버튼을 관객 수 밑에 가운데 정렬로 추가
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),  // 관객 수와 검색 버튼 사이의 간격
                contentAlignment = Alignment.Center  // 가운데 정렬
            ) {
                Button(onClick = {
                    // 검색 버튼 클릭 시 수행할 작업
                    Log.d("MovieRankPage", "검색 버튼 클릭됨")
                }) {
                    Text(text = "검색")
                }
            }
        }
    }
}



@Composable
fun ToggleButtonCompose(checked: Boolean, onCheckedChange: (Boolean) -> Unit, text: String) {
    val tint by animateColorAsState(if (checked) Color.Green else Color.LightGray)
    val textColor = if (checked) Color.Black else Color.White

    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = Modifier
            .clip(CircleShape)
            .border(1.dp, Color.Transparent, CircleShape)
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
fun SingleDatePicker(context: Context) {
    var selectedDate by remember { mutableStateOf("") }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val calendar = Calendar.getInstance()

    // remember 상태로 선택된 날짜 기억
    var year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var month by remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    var day by remember { mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = {
            showDatePickerDialog = true
            Log.d("SingleDatePicker", "DatePickerDialog 열림")
        }) {
            Text(text = if (selectedDate.isEmpty()) "날짜 선택" else selectedDate)
        }

        if (showDatePickerDialog) {
            // 선택된 날짜로 다이얼로그 표시
            val datePickerDialog = android.app.DatePickerDialog(
                context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    year = selectedYear
                    month = selectedMonth
                    day = selectedDay
                    selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    Log.d("SingleDatePicker", "날짜 선택됨: $selectedDate")
                    showDatePickerDialog = false
                },
                year, month, day
            )
            datePickerDialog.show()
            showDatePickerDialog = false
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerWithAutoWeek(context: Context) {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var showStartDatePickerDialog by remember { mutableStateOf(false) }
    val calendar = Calendar.getInstance()

    // remember 상태로 선택된 날짜 기억
    var year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var month by remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    var day by remember { mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }

    // 주간 범위 업데이트 함수
    fun updateWeekRange(selectedYear: Int, selectedMonth: Int, selectedDay: Int) {
        val cal = Calendar.getInstance()

        // 선택된 날짜로 캘린더 설정
        cal.set(selectedYear, selectedMonth, selectedDay)
        Log.d("DateRangePicker", "캘린더에 설정된 날짜: ${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH) + 1}-${cal.get(Calendar.DAY_OF_MONTH)}")

        // 주간의 일요일 찾기
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val startYear = cal.get(Calendar.YEAR)
        val startMonth = cal.get(Calendar.MONTH) + 1  // 표시용으로만 +1
        val startDay = cal.get(Calendar.DAY_OF_MONTH)
        startDate = "$startYear-$startMonth-$startDay"

        // 주간의 토요일 찾기
        cal.add(Calendar.DAY_OF_WEEK, 6)
        val endYear = cal.get(Calendar.YEAR)
        val endMonth = cal.get(Calendar.MONTH) + 1  // 표시용으로만 +1
        val endDay = cal.get(Calendar.DAY_OF_MONTH)
        endDate = "$endYear-$endMonth-$endDay"

        Log.d("DateRangePicker", "주간 설정됨: $startDate ~ $endDate")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = {
            showStartDatePickerDialog = true
            Log.d("DateRangePicker", "주간 DatePickerDialog 열림")
        }) {
            Text(text = if (startDate.isEmpty() && endDate.isEmpty()) {
                "기간 선택"
            } else {
                "기간: $startDate ~ $endDate"
            })
        }

        if (showStartDatePickerDialog) {
            val startDatePickerDialog = android.app.DatePickerDialog(
                context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // 날짜 선택 시 로그 출력
                    Log.d("DateRangePicker", "날짜 선택됨: $selectedYear-${selectedMonth + 1}-$selectedDay")

                    // Calendar.set()에 선택한 날짜를 적용
                    year = selectedYear
                    month = selectedMonth
                    day = selectedDay

                    // 주간 업데이트 호출
                    updateWeekRange(selectedYear, selectedMonth, selectedDay)
                    showStartDatePickerDialog = false
                },
                year, month, day
            )
            startDatePickerDialog.show()
            showStartDatePickerDialog = false
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMovieRankPage() {
    MovieRankPage()
}