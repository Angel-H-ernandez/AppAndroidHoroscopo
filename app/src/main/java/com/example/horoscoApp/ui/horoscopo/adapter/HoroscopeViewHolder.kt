package com.example.horoscoApp.ui.horoscopo.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscoApp.domain.model.HoroscopeInfo
import com.example.intermedioappkottlin.databinding.ItemHoroscopeBinding

class HoroscopeViewHolder(view : View) : RecyclerView.ViewHolder(view){
    private val binding  = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit){
        val context = binding.ivHoroscope.context
        binding.tvNombre.text = context.getString(horoscopeInfo.name)
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)

        binding.parent.setOnClickListener {
            StartRotationAimation(binding.ivHoroscope, newLanda  = {onItemSelected(horoscopeInfo)})  //lanza animación de rotación
          //  onItemSelected(horoscopeInfo)
        }
    }

    fun StartRotationAimation(view: View, newLanda:() -> Unit){
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()//flujo de la animaciom
            rotationBy(360f)
            withEndAction(newLanda) //codigoo que se ejecuta despues de terminarr la animacion
            start()

        }
    }
}