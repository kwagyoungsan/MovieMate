package com.example.moviemate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviemate.model.response.WeeklyBoxOffice

class WeeklyViewModel : ViewModel() {
    private val TAG = "DailyViewModel"

    private val _result = MutableLiveData<List<WeeklyBoxOffice>?>()
    val result: MutableLiveData<List<WeeklyBoxOffice>?>
        get() = _result

}