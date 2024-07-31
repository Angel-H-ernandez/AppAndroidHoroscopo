package com.example.horoscoApp.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading) //estado inicial
    var state: StateFlow<HoroscopeDetailState> = _state
}