package com.example.moviemate.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviemate.util.UiState
import com.example.moviemate.util.formatDate
import com.example.moviemate.view.MovieDetail
import com.example.moviemate.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(
    movieCd: String,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val detailState by viewModel.detailState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchDetailInfo(movieCd)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("영화 상세 정보") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "뒤로 가기")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            when (val state = detailState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Success -> {
                    DetailContent(detail = state.data)
                }

                is UiState.Error -> {
                    Text(
                        text = "에러: ${state.message}",
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailContent(detail: MovieDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        InfoText("🎬 영화명", detail.movieNm)
        InfoText("📅 제작년도", detail.prdtYear)
        InfoText("⏱️ 상영시간", "${detail.showTm} 분")
        InfoText("📅 개봉일", formatDate(detail.openDt))
        InfoText("🎥 제작상태", detail.prdtStatNm)
        InfoText("🌍 제작국가", detail.nations.joinToString { it.nationNm })
        InfoText("🎭 장르", detail.genreNm)
        InfoText("🎬 감독", detail.directors.joinToString { it.peopleNm })
        InfoText("⭐ 배우", detail.actors.joinToString { it.peopleNm })

        detail.cast?.takeIf { it.isNotBlank() }?.let {
            InfoText("👤 배역", it)
        }

        InfoText("🔞 관람등급", detail.watchGradeNm)
    }
}

@Composable
private fun InfoText(label: String, value: String) {
    Text("$label: $value", style = MaterialTheme.typography.bodyMedium)
}