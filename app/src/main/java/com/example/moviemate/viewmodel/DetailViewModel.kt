package com.example.moviemate.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviemate.model.MyApi
import com.example.moviemate.model.RetrofitInstance
import com.example.moviemate.repository.DetailRepository
import com.example.moviemate.repository.DetailRepositoryImpl
import com.example.moviemate.util.UiState
import com.example.moviemate.view.MovieDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel(
    private val repository: DetailRepository = DetailRepositoryImpl(
        RetrofitInstance.getMovieInfo()!!.create(MyApi::class.java)
    )
) : ViewModel() {

    private val _detailState = MutableStateFlow<UiState<MovieDetail>>(UiState.Loading)
    val detailState: StateFlow<UiState<MovieDetail>> = _detailState

    fun fetchDetailInfo(movieCd: String) {
        _detailState.value = UiState.Loading
        repository.fetchMovieDetail(movieCd) { result ->
            result.fold(
                onSuccess = { _detailState.value = UiState.Success(it) },
                onFailure = { _detailState.value = UiState.Error(it.message ?: "불러오기 실패") }
            )
        }
    }
}

