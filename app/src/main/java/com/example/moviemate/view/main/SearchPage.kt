package com.example.moviemate.view.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import com.example.moviemate.view.MovieSearchResult
import com.example.moviemate.util.UiState
import com.example.moviemate.util.formatDate
import com.example.moviemate.viewmodel.SearchViewModel

@Composable
fun SearchPage(
    navController: NavController,
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("선택") }
    val options = listOf("선택", "영화명", "감독명")
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    val searchState by viewModel.searchState.collectAsState()

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box {
                    Button(onClick = { expanded = true }) {
                        Text(selectedOption)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        options.forEach { label ->
                            DropdownMenuItem(
                                text = { Text(label) },
                                onClick = {
                                    selectedOption = label
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                TextField(
                    value = textState,
                    onValueChange = { textState = it },
                    placeholder = { Text("내용을 입력해주세요.") },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                )

                Button(
                    onClick = {
                        keyboardController?.hide()

                        when (selectedOption) {
                            "선택" -> {
                                Toast.makeText(context, "검색할 카테고리를 설정 후 검색해주세요.", Toast.LENGTH_SHORT).show()
                            }
                            "영화명" -> {
                                viewModel.searchMovieByMovieNm(textState.text)
                            }
                            "감독명" -> {
                                viewModel.searchMovieByDirectorNm(textState.text)
                            }
                        }
                    },
                    modifier = Modifier.height(56.dp)
                ) {
                    Text("검색")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (searchState) {
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success -> {
                    val movies = (searchState as UiState.Success<List<MovieSearchResult>>).data

                    if (movies.isEmpty()) {
                        Text("검색 결과가 없습니다.", style = MaterialTheme.typography.bodyMedium)
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(movies) { movie ->
                                Column(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth()
                                        .clickable {
                                            navController.navigate("detail/${movie.movieCd}")
                                        }
                                ) {
                                    Text("🎬 ${movie.movieNm} (${movie.prdtYear})", style = MaterialTheme.typography.bodyLarge)
                                    Text("개봉일: ${formatDate(movie.openDt)}", style = MaterialTheme.typography.bodyMedium)
                                    Text("유형: ${movie.typeNm}", style = MaterialTheme.typography.bodySmall)
                                    Text("감독: ${movie.directors.joinToString { it.peopleNm }}", style = MaterialTheme.typography.bodySmall)
                                    Text("배우: ${movie.actors.joinToString { it.peopleNm }}", style = MaterialTheme.typography.bodySmall)
                                    Divider(modifier = Modifier.padding(top = 8.dp))
                                }
                            }
                        }
                    }
                }

                is UiState.Error -> {
                    Text(
                        text = "에러: ${(searchState as UiState.Error).message}",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

