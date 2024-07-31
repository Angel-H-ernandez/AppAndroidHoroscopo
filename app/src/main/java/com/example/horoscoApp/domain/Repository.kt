package com.example.horoscoApp.domain


import com.example.horoscoApp.domain.model.PredictionModel

interface Repository {

    suspend fun getPrediction(sign: String): PredictionModel?
}