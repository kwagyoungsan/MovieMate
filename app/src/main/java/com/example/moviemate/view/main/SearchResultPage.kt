package com.example.moviemate.view.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviemate.model.DailyBoxOffice
import com.example.moviemate.model.WeeklyBoxOffice

@Composable
fun SearchDailyResultPage(searchResults: List<DailyBoxOffice>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "검색 결과",
            style = MaterialTheme.typography.titleLarge
        )

        searchResults.forEach { movie ->
            Text(
                text = "${movie.rank}. ${movie.movieNm} (${movie.openDt}) - 누적 관객수: ${movie.audiAcc}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun SearchWeeklyResultPage(searchResults: List<WeeklyBoxOffice>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "검색 결과",
            style = MaterialTheme.typography.titleLarge
        )

        searchResults.forEach { movie ->
            Text(
                text = "${movie.rank}. ${movie.movieNm} (${movie.openDt}) - 누적 관객수: ${movie.audiAcc}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
