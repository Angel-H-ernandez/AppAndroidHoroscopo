package com.example.horoscoApp.ui.detail

import com.example.horoscoApp.domain.model.HoroscopeModel

sealed class HoroscopeDetailState {
    data object Loading:HoroscopeDetailState()
    data class Error(val error: String):HoroscopeDetailState()
    data class Success(val prediction: String, val sign:String, val horosocopeModel:HoroscopeModel): HoroscopeDetailState()
}