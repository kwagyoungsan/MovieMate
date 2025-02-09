package com.example.moviemate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviemate.model.DailyBoxOffice

class DailyViewModel : ViewModel() {
    private val TAG = "DailyViewModel"

    private val _result = MutableLiveData<List<DailyBoxOffice>?>()
    val result: MutableLiveData<List<DailyBoxOffice>?>
        get() = _result

}