package com.example.horoscoApp.data.provider

import com.example.horoscoApp.domain.model.HoroscopeInfo
import com.example.horoscoApp.domain.model.HoroscopeInfo.*;
import javax.inject.Inject

class HoroscopeProvider @Inject constructor(){

    fun getHoroscope() : List<HoroscopeInfo> {
        return listOf(
             Aries,
            Taurus,
            Gemini,
            Cancer,
            Leo,
            Virgo,
            Libra,
            Scorpio,
            Sagittarius,
            Capricorn,
            Aquarius,
            Pisces
        )
    }
}