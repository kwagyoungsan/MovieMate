package com.example.moviemate.view.main

import android.content.Context
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviemate.model.Constants
import com.example.moviemate.model.DailyBoxOffice
import com.example.moviemate.model.DailyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

// Spacer for consistent spacing
@Composable
fun Spacer(num: Int) {
    Spacer(modifier = Modifier.height(num.dp))
}

// Single Date Picker
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleDatePicker(context: Context) {
    var selectedDate by remember { mutableStateOf("") }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), // Fixed height
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { showDatePickerDialog = true },
            modifier = Modifier
                .height(48.dp)
                .width(200.dp) // Same width as DateRangePicker
        ) {
            Text(
                text = if (selectedDate.isEmpty()) "날짜 선택" else selectedDate,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (showDatePickerDialog) {
            val datePickerDialog = android.app.DatePickerDialog(
                context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    showDatePickerDialog = false
                },
                year, month, day
            )
            datePickerDialog.show()
        }
    }
}

// Date Range Picker with Week Auto-Calculation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerWithAutoWeek(context: Context) {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var showStartDatePickerDialog by remember { mutableStateOf(false) }
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    fun updateWeekRange(selectedYear: Int, selectedMonth: Int, selectedDay: Int) {
        val cal = Calendar.getInstance()
        cal.set(selectedYear, selectedMonth, selectedDay)

        // Calculate start and end of the week
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val startYear = cal.get(Calendar.YEAR)
        val startMonth = cal.get(Calendar.MONTH) + 1
        val startDay = cal.get(Calendar.DAY_OF_MONTH)
        startDate = "$startYear-$startMonth-$startDay"

        cal.add(Calendar.DAY_OF_WEEK, 6)
        val endYear = cal.get(Calendar.YEAR)
        val endMonth = cal.get(Calendar.MONTH) + 1
        val endDay = cal.get(Calendar.DAY_OF_MONTH)
        endDate = "$endYear-$endMonth-$endDay"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), // Fixed height
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { showStartDatePickerDialog = true },
            modifier = Modifier
                .height(48.dp)
                .width(200.dp) // Fixed width
        ) {
            Text(
                text = if (startDate.isEmpty() && endDate.isEmpty()) {
                    "기간 선택"
                } else {
                    "$startDate ~ $endDate"
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (showStartDatePickerDialog) {
            val datePickerDialog = android.app.DatePickerDialog(
                context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    updateWeekRange(selectedYear, selectedMonth, selectedDay)
                    showStartDatePickerDialog = false
                },
                year, month, day
            )
            datePickerDialog.show()
        }
    }
}

@Composable
fun ToggleButtonCompose(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String
) {
    // 클릭 상태에 따라 연두색 사용
    val backgroundColor by animateColorAsState(
        if (checked) Color(0xFF32CD32) else Color.LightGray
    )
    val textColor by animateColorAsState(
        if (checked) Color.White else Color.Black
    )

    Button(
        onClick = { onCheckedChange(!checked) },
        modifier = Modifier
            .height(48.dp)
            .width(130.dp), // 고정 크기
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = backgroundColor, // 배경색
            contentColor = textColor // 텍스트 색상
        )
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// Period Selection Row
@Composable
fun PeriodSelectionRow(
    dayButton: Boolean,
    weekButton: Boolean,
    onButtonSelected: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ToggleButtonCompose(
            checked = dayButton,
            onCheckedChange = { onButtonSelected(true) },
            text = "일간"
        )
        ToggleButtonCompose(
            checked = weekButton,
            onCheckedChange = { onButtonSelected(false) },
            text = "주간"
        )
    }
}

// Date Selection Row
@Composable
fun DateSelectionRow(dayButton: Boolean) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), // Fixed height
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (dayButton) {
            SingleDatePicker(context)
        } else {
            DateRangePickerWithAutoWeek(context)
        }
    }
}

// Audience Range Selection
@Composable
fun AudienceRangeSelectionRow(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val options = listOf("0~50만", "50~100만", "100~500만", "500~1000만", "1000만 이상")
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(options) { option ->
            ToggleButtonCompose(
                checked = selectedOption == option,
                onCheckedChange = { onOptionSelected(option) },
                text = option
            )
        }
    }
}

// Main Page
@Composable
fun MovieRankPage() {
    val dailyData = DailyData.instance
    var searchResults by remember { mutableStateOf<List<DailyBoxOffice>?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val targetDate = 20241214 // API 요청 날짜

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp), // 간격 일정하게 유지
            horizontalAlignment = Alignment.Start
        ) {
            // 기존 UI - 일간/주간 선택
            PeriodSelectionRow(
                dayButton = true,
                weekButton = false
            ) { /* 일간/주간 버튼 상태 관리 로직 */ }

            // 기존 UI - 날짜 선택
            DateSelectionRow(dayButton = true)

            // 기존 UI - 관객 수 범위 선택
            AudienceRangeSelectionRow(
                selectedOption = "0~50만"
            ) { /* 관객 수 범위 선택 상태 관리 로직 */ }

            // 검색 버튼
            Button(
                onClick = {
                    isLoading = true
                    errorMessage = null
                    searchResults = null

                    // API 호출
                    dailyData.getDailyData(
                        date = targetDate,
                        onSuccess = { results ->
                            searchResults = results
                            isLoading = false
                        },
                        onFailure = { error ->
                            errorMessage = error.message
                            isLoading = false
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("검색")
            }

            // 로딩 상태 표시
            if (isLoading) {
                Text("로딩 중...", style = MaterialTheme.typography.bodyMedium)
            }

            // 에러 메시지 표시
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // 검색 결과 표시 (검색 버튼 아래)
            searchResults?.let { results ->
                SearchResultPage(searchResults = results)
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewMovieRankPage() {
    MovieRankPage()
}
