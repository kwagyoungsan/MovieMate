package com.example.moviemate.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemate.BuildConfig
import com.example.moviemate.model.DetailInfo
import com.example.moviemate.model.DetailInfoResponse
import com.example.moviemate.model.MyApi
import com.example.moviemate.model.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _detailInfo = MutableStateFlow<DetailInfo?>(null)
    val detailInfo: StateFlow<DetailInfo?> = _detailInfo

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    companion object {
        private const val TAG = "DetailViewModel"
    }

    fun fetchDetailInfo(movieCd: String) {
        viewModelScope.launch {
            val apiService = RetrofitInstance.getMovieInfo()?.create(MyApi::class.java)

            val call = apiService?.getDetailInfo(
                apiKey = BuildConfig.API_KEY,
                movieCd = movieCd.toInt()
            )

            Log.d(TAG, "API 요청 URL: ${call?.request()?.url}")

            call?.enqueue(object : Callback<DetailInfoResponse?> {
                override fun onResponse(
                    call: Call<DetailInfoResponse?>,
                    response: Response<DetailInfoResponse?>
                ) {
                    if (response.isSuccessful) {
                        val movieInfo = response.body()?.movieInfoResult?.movieInfo
                        if (movieInfo != null) {
                            _detailInfo.value = movieInfo

                            Log.d(TAG, "API 응답 성공: $movieInfo")
                        } else {
                            _errorMessage.value = "영화 정보를 찾을 수 없습니다."
                            Log.e(TAG, "응답 성공했지만 movieInfo가 null임")
                        }
                    } else {
                        _errorMessage.value = "서버 응답 실패: ${response.code()}"
                        Log.e(TAG, "서버 응답 실패: ${response.code()}, ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<DetailInfoResponse?>, t: Throwable) {
                    Log.e(TAG, "API 호출 실패", t)
                    _errorMessage.value = "네트워크 오류: ${t.message}"
                }
            })

        }
    }
}
