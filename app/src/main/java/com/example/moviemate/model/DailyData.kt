package com.example.moviemate.model

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DailyData {
    companion object {
        val instance = DailyData()
        private const val TAG = "DailyData"
    }

    fun getDailyData(date: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl(API.BASE_URL_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI = retrofit.create(MyApi::class.java)

        val call: Call<List<DailyBoxOffice>> = retrofitAPI.getTargetDt(date)

        call.enqueue(object : Callback<List<DailyBoxOffice>?> {
            override fun onResponse(
                call: Call<List<DailyBoxOffice>?>,
                response: Response<List<DailyBoxOffice>?>
            ) {
                if (response.isSuccessful) {
                    val lst: List<DailyBoxOffice> = response.body() ?: ArrayList()

                    Log.d(TAG, "Success: ${response.body()}")
                } else {
                    Log.e(TAG, "Response not successful: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<DailyBoxOffice>?>, t: Throwable) {
                Log.e(TAG, "Failure: ${t.message}", t)
            }
        })
    }
}