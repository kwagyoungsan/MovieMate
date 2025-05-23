package com.example.moviemate.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviemate.view.MovieSearchResult
import com.example.moviemate.model.MyApi
import com.example.moviemate.model.RetrofitInstance
import com.example.moviemate.repository.MovieRepository
import com.example.moviemate.repository.MovieRepositoryImpl
import com.example.moviemate.repository.SearchRepository
import com.example.moviemate.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _searchState = MutableStateFlow<UiState<List<MovieSearchResult>>>(UiState.Success(emptyList()))
    val searchState: StateFlow<UiState<List<MovieSearchResult>>> = _searchState

    fun searchMovieByMovieNm(query: String) {
        _searchState.value = UiState.Loading
        repository.searchByMovieNm(query) { result ->
            result.fold(
                onSuccess = { _searchState.value = UiState.Success(it) },
                onFailure = { _searchState.value = UiState.Error(it.message ?: "오류 발생") }
            )
        }
    }

    fun searchMovieByDirectorNm(query: String) {
        _searchState.value = UiState.Loading
        repository.searchByDirectorNm(query) { result ->
            result.fold(
                onSuccess = { _searchState.value = UiState.Success(it) },
                onFailure = { _searchState.value = UiState.Error(it.message ?: "오류 발생") }
            )
        }
    }
}
