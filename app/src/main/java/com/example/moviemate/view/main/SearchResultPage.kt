package com.example.moviemate.view.main

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviemate.model.response.DailyBoxOffice
import com.example.moviemate.model.response.WeeklyBoxOffice
import com.example.moviemate.util.formatDate

@Composable
fun MovieBoxOfficeCard(
    rank: String,
    movieNm: String,
    openDt: String,
    audiAcc: String,
    isNew: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSystemInDarkTheme()) Color.Black else Color.White

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            if (isNew) {
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Red, shape = RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("NEW", color = Color.Red, style = MaterialTheme.typography.labelMedium)
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            Text("${rank}위. $movieNm", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text("개봉일: ${formatDate(openDt)}", style = MaterialTheme.typography.bodySmall)
            Text("누적 관객수: $audiAcc", style = MaterialTheme.typography.bodySmall)
        }
    }

    Divider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun SearchDailyResultPage(
    searchResults: List<DailyBoxOffice>,
    onMovieClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "영화 클릭 시 상세 정보를 확인할 수 있습니다.",
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(searchResults) { movie ->
                MovieBoxOfficeCard(
                    rank = movie.rank,
                    movieNm = movie.movieNm,
                    openDt = movie.openDt,
                    audiAcc = movie.audiAcc,
                    isNew = movie.rankOldAndNew == "NEW",
                    onClick = { onMovieClick(movie.movieCd) }
                )
            }
        }
    }
}

@Composable
fun SearchWeeklyResultPage(
    searchResults: List<WeeklyBoxOffice>,
    onMovieClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "영화 클릭 시 상세 정보를 확인할 수 있습니다.",
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(searchResults) { movie ->
                MovieBoxOfficeCard(
                    rank = movie.rank,
                    movieNm = movie.movieNm,
                    openDt = movie.openDt,
                    audiAcc = movie.audiAcc,
                    isNew = movie.rankOldAndNew == "NEW",
                    onClick = { onMovieClick(movie.movieCd) }
                )
            }
        }
    }
}
