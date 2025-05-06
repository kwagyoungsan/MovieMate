package com.example.moviemate.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviemate.R
import com.example.moviemate.util.UiState
import com.example.moviemate.util.formatDate
import com.example.moviemate.view.MovieDetail
import com.example.moviemate.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(
    movieCd: String,
    navController: NavController,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
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
                .background(Color.White)
        ) {
            when (detailState) {
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success -> {
                    val detail = (detailState as UiState.Success<MovieDetail>).data

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text("🎬 영화명: ${detail.movieNm}")
                        Text("📅 제작년도: ${detail.prdtYear}")
                        Text("⏱️ 상영시간: ${detail.showTm} 분")
                        Text("📅 개봉일: ${formatDate(detail.openDt)}")
                        Text("🎥 제작상태: ${detail.prdtStatNm}")
                        Text("🌍 제작국가: ${detail.nations.joinToString { it.nationNm }}")
                        Text("🎭 장르: ${detail.genreNm}")
                        Text("🎬 감독: ${detail.directors.joinToString { it.peopleNm }}")
                        Text("⭐ 배우: ${detail.actors.joinToString { it.peopleNm }}")

                        if (!detail.cast.isNullOrEmpty()) {
                            Text("👤 배역: ${detail.cast}")
                        }

                        Text("🔞 관람등급: ${detail.watchGradeNm}")
                    }
                }

                is UiState.Error -> {
                    Text(
                        text = "에러: ${(detailState as UiState.Error).message}",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

