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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.moviemate.model.Constants
import com.example.moviemate.model.customButtonModifier
import java.time.LocalDate
import java.util.Calendar

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

    var year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var month by remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    var day by remember { mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = {
            showDatePickerDialog = true
            Log.d("SingleDatePicker", "DatePickerDialog 열림")
        }) {
            Text(text = if (selectedDate.isEmpty()) "날짜 선택" else selectedDate)
        }

        if (showDatePickerDialog) {
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

    var year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var month by remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    var day by remember { mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }

    fun updateWeekRange(selectedYear: Int, selectedMonth: Int, selectedDay: Int) {
        val cal = Calendar.getInstance()

        cal.set(selectedYear, selectedMonth, selectedDay)
        Log.d("DateRangePicker", "캘린더에 설정된 날짜: ${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH) + 1}-${cal.get(Calendar.DAY_OF_MONTH)}")

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
                    Log.d("DateRangePicker", "날짜 선택됨: $selectedYear-${selectedMonth + 1}-$selectedDay")

                    year = selectedYear
                    month = selectedMonth
                    day = selectedDay

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

@Composable
fun ToggleButtonCompose(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String
) {
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = Modifier.customButtonModifier(checked)
    ) {
        Text(
            text = text,
            color = if (checked) Color.Black else Color.White,
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

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

@Composable
fun DateSelectionRow(dayButton: Boolean) {
    val context = LocalContext.current

    if (dayButton) {
        SingleDatePicker(context)
    } else {
        DateRangePickerWithAutoWeek(context)
    }
}

@Composable
fun AudienceRangeSelectionRow(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val options = Constants.ranges
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

@Composable
fun SearchButton(selectedOption: String) {
    Button(
        onClick = {
            Log.d("SearchButton", "검색 버튼 클릭됨, 선택된 옵션: $selectedOption")
            // 검색 로직 추가 (예: API 호출)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(text = "검색")
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMovieRankPage() {
    MovieRankPage()
}

@Composable
fun MovieRankPage() {
    var selectedOption by remember { mutableStateOf(Constants.ranges.first()) }
    var dayButton by remember { mutableStateOf(true) }
    var weekButton by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ToggleButtonCompose(
                    checked = dayButton,
                    onCheckedChange = { dayButton = true; weekButton = false },
                    text = "일간"
                )
                ToggleButtonCompose(
                    checked = weekButton,
                    onCheckedChange = { dayButton = false; weekButton = true },
                    text = "주간"
                )
            }

            Row(
                modifier = Modifier.padding(start = 0.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                DateSelectionRow(dayButton)
            }

            AudienceRangeSelectionRow(selectedOption) { option ->
                selectedOption = option
            }

            SearchButton(selectedOption)
        }
    }
}