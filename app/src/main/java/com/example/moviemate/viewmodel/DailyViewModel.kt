package com.example.moviemate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemate.model.DailyBoxOffice
import kotlinx.coroutines.launch

class DailyViewModel : ViewModel() {
    private val TAG = "DailyViewModel"

    private val _result = MutableLiveData<List<DailyBoxOffice>?>()
    val result: MutableLiveData<List<DailyBoxOffice>?>
        get() = _result




}