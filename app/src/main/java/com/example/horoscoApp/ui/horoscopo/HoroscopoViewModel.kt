package com.example.horoscoApp.ui.horoscopo

import androidx.lifecycle.ViewModel
import com.example.horoscoApp.domain.model.HoroscopeInfo
import com.example.horoscoApp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopoViewModel @Inject constructor() : ViewModel() {

    private val _horoscopo = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    var horoscopo: StateFlow<List<HoroscopeInfo>> = _horoscopo

    init {
        _horoscopo.value = listOf(
            Aries, Taurus, Gemini, Cancer, Leo, Virgo, Libra, Scorpio, Sagittarius, Capricorn, Aquarius, Pisces
        )
    }
}