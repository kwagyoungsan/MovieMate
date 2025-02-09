import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviemate.model.DailyBoxOffice
import com.example.moviemate.model.WeeklyBoxOffice

@Composable
fun SearchDailyResultPage(searchResults: List<DailyBoxOffice>, onMovieClick: (DailyBoxOffice) -> Unit) {
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
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(searchResults) { movie ->
                MovieItem(movie.movieNm, movie.openDt, movie.audiAcc, movie.rankInten, movie.rankOldAndNew) {
                    onMovieClick(movie)
                }
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun SearchWeeklyResultPage(searchResults: List<WeeklyBoxOffice>, onMovieClick: (WeeklyBoxOffice) -> Unit) {
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
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(searchResults) { movie ->
                MovieItem(movie.movieNm, movie.openDt, movie.audiAcc, movie.rankInten, movie.rankOldAndNew) {
                    onMovieClick(movie)
                }
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun MovieItem(movieNm: String, openDt: String, audiAcc: String, rankInten: String, rankOldAndNew: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 왼쪽: 영화 정보
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = movieNm, style = MaterialTheme.typography.titleMedium)
            Text(text = "개봉일: $openDt", style = MaterialTheme.typography.bodySmall)
            Text(text = "누적 관객수: $audiAcc", style = MaterialTheme.typography.bodySmall)
        }

        // 오른쪽: 랭킹 정보
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = rankInten, style = MaterialTheme.typography.bodyMedium, color = Color.Red)
            Text(text = rankOldAndNew, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
