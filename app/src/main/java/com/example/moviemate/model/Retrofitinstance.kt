package com.example.moviemate.model

import com.example.moviemate.model.API.BASE_URL_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private var retrofitClient: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofitClient == null) {
            retrofitClient = Retrofit.Builder()
                .baseUrl("http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/") // Base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient
    }
}