package com.example.moviemate.view.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviemate.model.response.DailyBoxOffice
import com.example.moviemate.model.response.WeeklyBoxOffice
import com.example.moviemate.util.UiState
import com.example.moviemate.viewmodel.MovieRankViewModel
import java.util.Calendar

const val TAG = "MovieRankPage"

// Single Date Picker
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleDatePicker(
    context: Context,
    onDateSelected: (String) -> Unit) {
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
                    val formattedDate = String.format(
                        "%04d-%02d-%02d",
                        selectedYear, selectedMonth + 1, selectedDay
                    )
                    selectedDate = formattedDate
                    onDateSelected(formattedDate.replace("-", "")) // API용 포맷 전달
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
fun DateRangePickerWithAutoWeek(
    context: Context,
    onDateSelected: (String) -> Unit,
    onRangeSelected: (String, String) -> Unit // 시작일과 종료일 전달
) {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var showStartDatePickerDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    fun updateWeekRange(selectedYear: Int, selectedMonth: Int, selectedDay: Int) {
        val cal = Calendar.getInstance()
        cal.set(selectedYear, selectedMonth, selectedDay)

        val formattedDate = String.format(
            "%04d-%02d-%02d",
            selectedYear, selectedMonth + 1, selectedDay
        )
        selectedDate = formattedDate
        onDateSelected(formattedDate.replace("-", ""))

        // 시작일 계산
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val startYear = cal.get(Calendar.YEAR)
        val startMonth = cal.get(Calendar.MONTH) + 1
        val startDay = cal.get(Calendar.DAY_OF_MONTH)
        startDate = String.format("%04d%02d%02d", startYear, startMonth, startDay)

        // 종료일 계산
        cal.add(Calendar.DAY_OF_WEEK, 6)
        val endYear = cal.get(Calendar.YEAR)
        val endMonth = cal.get(Calendar.MONTH) + 1
        val endDay = cal.get(Calendar.DAY_OF_MONTH)
        endDate = String.format("%04d%02d%02d", endYear, endMonth, endDay)

        onRangeSelected(startDate, endDate) // 상위로 전달
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { showStartDatePickerDialog = true },
            modifier = Modifier
                .height(48.dp)
                .width(200.dp)
        ) {
            Text(
                text = if (startDate.isEmpty() && endDate.isEmpty()) "기간 선택" else "$startDate ~ $endDate",
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
fun DateSelectionRow(
    dayButton: Boolean,
    onDateSelected: (String) -> Unit, // 일간 날짜 전달
    onRangeSelected: (String, String) -> Unit // 주간 범위 전달
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (dayButton) {
            SingleDatePicker(context = context, onDateSelected = onDateSelected)
        } else {
            DateRangePickerWithAutoWeek(context = context, onDateSelected = onDateSelected, onRangeSelected = onRangeSelected)
        }
    }
}

@Composable
fun MovieRankPage(
    navController: NavController,
    viewModel: MovieRankViewModel = hiltViewModel()
) {
    val dailyState by viewModel.dailyState.collectAsState()
    val weeklyState by viewModel.weeklyState.collectAsState()

    var dayButton by remember { mutableStateOf(true) }
    var targetDate by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) // 👈 버튼과 UI 요소 간 간격
    ) {

        // 기간 선택 (일간/주간 토글)
        PeriodSelectionRow(
            dayButton = dayButton,
            weekButton = !dayButton,
            onButtonSelected = { isDay -> dayButton = isDay }
        )

        // 날짜 선택
        DateSelectionRow(
            dayButton = dayButton,
            onDateSelected = { date -> targetDate = date },
            onRangeSelected = { _, _ -> }
        )

        // 검색 버튼
        Button(
            onClick = {
                if (targetDate.isBlank()) {
                    Toast.makeText(context, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    if (dayButton) viewModel.fetchDailyBoxOffice(targetDate.toInt())
                    else viewModel.fetchWeeklyBoxOffice(targetDate.toInt())
                }
            },
            modifier = Modifier.fillMaxWidth() // 👈 넓게
        ) {
            Text("검색")
        }

        // 검색 결과
        if (dayButton) {
            when (dailyState) {
                is UiState.Loading -> CircularProgressIndicator()
                is UiState.Success -> SearchDailyResultPage(
                    searchResults = (dailyState as UiState.Success).data,
                    onMovieClick = { navController.navigate("detail/$it") }
                )
                is UiState.Error -> Text("에러: ${(dailyState as UiState.Error).message}", color = Color.Red)
            }
        } else {
            when (weeklyState) {
                is UiState.Loading -> CircularProgressIndicator()
                is UiState.Success -> SearchWeeklyResultPage(
                    searchResults = (weeklyState as UiState.Success).data,
                    onMovieClick = { navController.navigate("detail/$it") }
                )
                is UiState.Error -> Text("에러: ${(weeklyState as UiState.Error).message}", color = Color.Red)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMovieRankPage() {
    val navController = rememberNavController()
    MovieRankPage(navController = navController)
}
