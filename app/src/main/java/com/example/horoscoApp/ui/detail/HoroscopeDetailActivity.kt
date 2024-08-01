package com.example.horoscoApp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscoApp.domain.model.HoroscopeModel
import com.example.intermedioappkottlin.R
import com.example.intermedioappkottlin.databinding.ActivityHoroscopeDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {


    private val args:HoroscopeDetailActivityArgs by  navArgs()
    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels() //debe ser val, da error si es un var
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        horoscopeDetailViewModel.getHoroscope(args.type)
    }

    private fun initUi() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
       binding.ivBack.setOnClickListener{
           onBackPressed()
       }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeDetailViewModel.state.collect { state ->
                    when(state){
                        is HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Error -> errorState()
                        is HoroscopeDetailState.Success -> successState(state)
                    }
                }
            }
        }
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.ProgressBar.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction

        val image: Int = when(state.horosocopeModel){
            HoroscopeModel.Aries -> R.drawable.aries
            HoroscopeModel.Taurus -> R.drawable.tauro
            HoroscopeModel.Gemini -> R.drawable.geminis
            HoroscopeModel.Cancer -> R.drawable.cancer
            HoroscopeModel.Leo -> R.drawable.leon
            HoroscopeModel.Virgo -> R.drawable.virgo
            HoroscopeModel.Libra -> R.drawable.libra
            HoroscopeModel.Scorpio -> R.drawable.escorpion
            HoroscopeModel.Sagittarius -> R.drawable.sagitario
            HoroscopeModel.Capricorn -> R.drawable.capricornio
            HoroscopeModel.Aquarius -> R.drawable.acuario
            HoroscopeModel.Pisces -> R.drawable.pisces
        }
        binding.ivDetail.setImageResource(image)
    }

    private fun errorState() {
        binding.ProgressBar.isVisible = false
    }

    private fun loadingState() {
        binding.ProgressBar.isVisible = true
    }
}