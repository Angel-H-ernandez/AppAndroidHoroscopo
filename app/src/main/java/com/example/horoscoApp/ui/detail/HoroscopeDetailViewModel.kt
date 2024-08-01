package com.example.horoscoApp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscoApp.domain.model.HoroscopeModel
import com.example.horoscoApp.domain.usescase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase): ViewModel() {
    private val _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading) //estado inicial
    var state: StateFlow<HoroscopeDetailState> = _state

    lateinit var horoscope:HoroscopeModel
    fun getHoroscope(sing: HoroscopeModel){
        horoscope = sing
        viewModelScope.launch {
            //hilo principañ

            _state.value = HoroscopeDetailState.Loading
            //termina la ejecución del hilo principal y cambia el estado al final de la llamada al API.
            val result = withContext(Dispatchers.IO){
                getPredictionUseCase(sing.name) //hilo secundario
            }
            if(result != null){
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sign, horoscope)
            }
            else{
                _state.value = HoroscopeDetailState.Error("Error al obtener el horóscopo.")
            }
            //hilo principal

        }
    }
}