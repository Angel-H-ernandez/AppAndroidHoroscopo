package com.example.horoscoApp.domain.usescase

import com.example.horoscoApp.domain.Repository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository:Repository) {

    //operator es paera sobrescribir
    suspend operator fun invoke(sign:String) = repository.getPrediction(sign)



    fun aris(){

    }
}