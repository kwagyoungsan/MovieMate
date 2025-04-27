package com.example.moviemate.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.moviemate.util.formatDate
import com.example.moviemate.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(
    movieCd: String,
    navController: NavController,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val detailInfo by viewModel.detailInfo.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchDetailInfo(movieCd)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("영화 상세 정보") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "뒤로 가기"
                        )
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
            when {
                detailInfo != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text("🎬 영화명: ${detailInfo?.movieNm ?: "정보 없음"}")
                        Text("📅 제작년도: ${detailInfo?.prdtYear ?: "정보 없음"}")
                        Text("⏱️ 상영시간: ${detailInfo?.showTm ?: "정보 없음"} 분")
                        Text("📅 개봉일: ${detailInfo?.openDt?.let { formatDate(it) } ?: "정보 없음"}")
                        Text("🎥 제작상태: ${detailInfo?.prdtStatNm ?: "정보 없음"}")

                        // List 타입들도 안전하게
                        Text("🌍 제작국가: ${detailInfo?.nations?.joinToString { it.nationNm } ?: "정보 없음"}")
                        Text("🎭 장르: ${detailInfo?.genreNm ?: "정보 없음"}")

                        Text("🎬 감독: ${
                            detailInfo?.directors?.joinToString { it.peopleNm } ?: "정보 없음"
                        }")

                        Text("⭐ 배우: ${
                            detailInfo?.actors?.joinToString { it.peopleNm } ?: "정보 없음"
                        }")

                        if (!detailInfo?.cast.isNullOrEmpty()) {
                            Text("👤 배역: ${detailInfo?.cast}")
                        }

                        Text("🔞 관람등급: ${detailInfo?.watchGradeNm ?: "정보 없음"}")
                    }

                }
                errorMessage != null -> {
                    // 에러가 발생한 경우
                    Text(
                        text = "에러: $errorMessage",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    // 로딩 중
                    Text(
                        text = "로딩 중...",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

