package com.example.moviemate.view.main

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
import com.example.moviemate.util.UiState
import com.example.moviemate.viewmodel.MovieRankViewModel
import java.util.*

@Composable
fun MovieRankPage(
    navController: NavController,
    viewModel: MovieRankViewModel = hiltViewModel()
) {
    val dailyState by viewModel.dailyState.collectAsState()
    val weeklyState by viewModel.weeklyState.collectAsState()

    var isDaily by remember { mutableStateOf(true) }
    var selectedDate by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PeriodSelectionRow(
            isDaily = isDaily,
            onSelectionChange = { isDaily = it }
        )

        DateSelectionRow(
            isDaily = isDaily,
            onDateSelected = { selectedDate = it },
            onRangeSelected = { _, _ -> }
        )

        Button(
            onClick = {
                if (selectedDate.isBlank()) {
                    Toast.makeText(context, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    val dateInt = selectedDate.toInt()
                    if (isDaily) viewModel.fetchDailyBoxOffice(dateInt)
                    else viewModel.fetchWeeklyBoxOffice(dateInt)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("검색")
        }

        when {
            isDaily && dailyState is UiState.Loading -> CircularProgressIndicator()
            isDaily && dailyState is UiState.Success -> {
                SearchDailyResultPage(
                    searchResults = (dailyState as UiState.Success).data,
                    onMovieClick = { navController.navigate("detail/$it") }
                )
            }
            isDaily && dailyState is UiState.Error -> Text(
                "에러: ${(dailyState as UiState.Error).message}",
                color = Color.Red
            )

            !isDaily && weeklyState is UiState.Loading -> CircularProgressIndicator()
            !isDaily && weeklyState is UiState.Success -> {
                SearchWeeklyResultPage(
                    searchResults = (weeklyState as UiState.Success).data,
                    onMovieClick = { navController.navigate("detail/$it") }
                )
            }
            !isDaily && weeklyState is UiState.Error -> Text(
                "에러: ${(weeklyState as UiState.Error).message}",
                color = Color.Red
            )
        }
    }
}

@Composable
fun PeriodSelectionRow(
    isDaily: Boolean,
    onSelectionChange: (Boolean) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ToggleButton("일간", isDaily) { onSelectionChange(true) }
        ToggleButton("주간", !isDaily) { onSelectionChange(false) }
    }
}

@Composable
fun ToggleButton(text: String, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor by animateColorAsState(if (selected) Color(0xFF32CD32) else Color.LightGray)
    val textColor by animateColorAsState(if (selected) Color.White else Color.Black)

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        modifier = Modifier
            .height(48.dp)
            .width(130.dp)
    ) {
        Text(text = text, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun DateSelectionRow(
    isDaily: Boolean,
    onDateSelected: (String) -> Unit,
    onRangeSelected: (String, String) -> Unit
) {
    val context = LocalContext.current

    if (isDaily) {
        SingleDatePicker(context, onDateSelected)
    } else {
        DateRangePickerWithAutoWeek(context, onDateSelected, onRangeSelected)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleDatePicker(
    context: Context,
    onDateSelected: (String) -> Unit
) {
    var selectedDate by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .height(48.dp)
                .width(200.dp)
        ) {
            Text(if (selectedDate.isEmpty()) "날짜 선택" else selectedDate)
        }

        if (showDialog) {
            android.app.DatePickerDialog(
                context,
                { _, y, m, d ->
                    selectedDate = "%04d-%02d-%02d".format(y, m + 1, d)
                    onDateSelected(selectedDate.replace("-", ""))
                    showDialog = false
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerWithAutoWeek(
    context: Context,
    onDateSelected: (String) -> Unit,
    onRangeSelected: (String, String) -> Unit
) {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .height(48.dp)
                .width(200.dp)
        ) {
            Text(if (startDate.isEmpty()) "기간 선택" else "$startDate ~ $endDate")
        }

        if (showDialog) {
            android.app.DatePickerDialog(
                context,
                { _, y, m, d ->
                    val cal = Calendar.getInstance()
                    cal.set(y, m, d)

                    val selected = "%04d-%02d-%02d".format(y, m + 1, d)
                    onDateSelected(selected.replace("-", ""))

                    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
                    startDate = "%04d%02d%02d".format(
                        cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)
                    )
                    cal.add(Calendar.DAY_OF_WEEK, 6)
                    endDate = "%04d%02d%02d".format(
                        cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)
                    )
                    onRangeSelected(startDate, endDate)
                    showDialog = false
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieRankPage() {
    val navController = rememberNavController()
    MovieRankPage(navController = navController)
}
