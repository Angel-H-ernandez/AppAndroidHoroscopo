package com.example.horoscoApp.domain.model

import com.example.intermedioappkottlin.R

sealed class HoroscopeInfo(val img: Int, val name: Int){
    object Aries: HoroscopeInfo(R.drawable.aries, R.string.aries)
    object Taurus: HoroscopeInfo(R.drawable.tauro, R.string.tauro)
    object Gemini: HoroscopeInfo(R.drawable.geminis, R.string.geminis)
    object Cancer: HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    object Leo: HoroscopeInfo(R.drawable.leon, R.string.leo)
    object Virgo: HoroscopeInfo(R.drawable.virgo, R.string.virgo)
    object Libra: HoroscopeInfo(R.drawable.libra, R.string.libra)
    object Scorpio: HoroscopeInfo(R.drawable.escorpion, R.string.escorpio)
    object Sagittarius: HoroscopeInfo(R.drawable.sagitario, R.string.sagitario)
    object Capricorn: HoroscopeInfo(R.drawable.capricornio, R.string.capricornio)
    object Aquarius: HoroscopeInfo(R.drawable.acuario, R.string.acuario)
    object Pisces: HoroscopeInfo(R.drawable.pisces, R.string.piscis)

}