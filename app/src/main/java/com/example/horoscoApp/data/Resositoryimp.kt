package com.example.horoscoApp.data

import android.util.Log
import com.example.horoscoApp.data.network.HoroscopeDataService
import com.example.horoscoApp.data.network.response.HoroscopePrediction
import com.example.horoscoApp.domain.Repository
import com.example.horoscoApp.domain.model.PredictionModel
import javax.inject.Inject

class Resositoryimp @Inject constructor(private val apiService: HoroscopeDataService): Repository{
    override suspend fun getPrediction(sign: String): PredictionModel?{
        kotlin.runCatching { apiService.getHoroscopeDetails(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.e("ERROR", "a ocurrido un error ${it.message}") }
        return null //Si hubo error, se devuelve null. Si no, se devuelve la respuesta del API.
    }
}