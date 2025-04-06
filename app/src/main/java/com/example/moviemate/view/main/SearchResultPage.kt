package com.example.moviemate.view.main

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviemate.model.DailyBoxOffice
import com.example.moviemate.model.WeeklyBoxOffice
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


@Composable
fun SearchDailyResultPage(
    searchResults: List<DailyBoxOffice>,
    onMovieClick: (String) -> Unit) {
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
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(searchResults) { movie ->
                val rankOldAndNew = movie.rankOldAndNew == "NEW"

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMovieClick(movie.movieCd) }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "${movie.rank}위. ${movie.movieNm}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("개봉일: ${movie.openDt}", style = MaterialTheme.typography.bodySmall)
                            Text("누적 관객수: ${movie.audiAcc}", style = MaterialTheme.typography.bodySmall)
                        }

                        if (rankOldAndNew) {
                            Box(
                                modifier = Modifier
                                    .border(1.dp, Color.Red, shape = RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("NEW", color = Color.Red, style = MaterialTheme.typography.labelMedium)
                            }
                        }
                    }
                }
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
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(searchResults) { movie ->
                val rankOldAndNew = movie.rankOldAndNew == "NEW"
                Log.d("DEBUG", "Movie: ${movie.movieNm}, rankOldAndNew: ${movie.rankOldAndNew}")  // ✅ 로그 추가

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMovieClick(movie.movieCd) }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "${movie.rank}위. ${movie.movieNm}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("개봉일: ${movie.openDt}", style = MaterialTheme.typography.bodySmall)
                            Text("누적 관객수: ${movie.audiAcc}", style = MaterialTheme.typography.bodySmall)
                        }

                        if (rankOldAndNew) {
                            Box(
                                modifier = Modifier
                                    .border(1.dp, Color.Red, shape = RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("NEW", color = Color.Red, style = MaterialTheme.typography.labelMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}

