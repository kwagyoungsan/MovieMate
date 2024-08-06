package com.example.moviemate.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    // http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=82ca741a2844c5c180a208137bb92bd7&targetDt=20120101
    //(BASE_URL) + api_key + &targetDt=20120101
    @GET(API.BASE_URL_KEY)
    fun getTargetDt(
        @Query("&targetDt=") targetDt: Int
    ) :Call<List<DailyBoxOffice>>
}