package com.example.moviemate.repository

import com.example.moviemate.model.response.DailyBoxOffice
import com.example.moviemate.model.response.WeeklyBoxOffice

interface MovieRankRepository {
    fun getDailyBoxOffice(date: String, onResult: (Result<List<DailyBoxOffice>>) -> Unit)
    fun getWeeklyBoxOffice(date: String, onResult: (Result<List<WeeklyBoxOffice>>) -> Unit)
}
