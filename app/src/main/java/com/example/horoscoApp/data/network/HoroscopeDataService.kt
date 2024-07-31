package com.example.horoscoApp.data.network

import com.example.horoscoApp.data.network.response.HoroscopePrediction
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeDataService {

    @GET("/{sign}")
    suspend fun getHoroscopeDetails(@Path("sign") sign: String): HoroscopePrediction
}