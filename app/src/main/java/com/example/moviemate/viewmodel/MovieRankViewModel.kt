package com.example.moviemate.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviemate.model.response.DailyBoxOffice
import com.example.moviemate.model.response.WeeklyBoxOffice
import com.example.moviemate.repository.MovieRankRepository
import com.example.moviemate.repository.MovieRankRepositoryImpl
import com.example.moviemate.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieRankViewModel(
    private val repository: MovieRankRepository = MovieRankRepositoryImpl()
) : ViewModel() {

    private val _dailyState = MutableStateFlow<UiState<List<DailyBoxOffice>>>(UiState.Success(emptyList()))
    val dailyState: StateFlow<UiState<List<DailyBoxOffice>>> = _dailyState

    private val _weeklyState = MutableStateFlow<UiState<List<WeeklyBoxOffice>>>(UiState.Success(emptyList()))
    val weeklyState: StateFlow<UiState<List<WeeklyBoxOffice>>> = _weeklyState

    fun fetchDailyBoxOffice(date: String) {
        _dailyState.value = UiState.Loading
        repository.getDailyBoxOffice(date) { result ->
            result.fold(
                onSuccess = { _dailyState.value = UiState.Success(it) },
                onFailure = { _dailyState.value = UiState.Error(it.message ?: "일간 랭킹 로드 실패") }
            )
        }
    }

    fun fetchWeeklyBoxOffice(date: String) {
        _weeklyState.value = UiState.Loading
        repository.getWeeklyBoxOffice(date) { result ->
            result.fold(
                onSuccess = { _weeklyState.value = UiState.Success(it) },
                onFailure = { _weeklyState.value = UiState.Error(it.message ?: "주간 랭킹 로드 실패") }
            )
        }
    }
}
