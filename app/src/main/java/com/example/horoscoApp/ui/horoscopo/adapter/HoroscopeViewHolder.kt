package com.example.horoscoApp.ui.horoscopo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscoApp.domain.model.HoroscopeInfo
import com.example.intermedioappkottlin.databinding.ItemHoroscopeBinding

class HoroscopeViewHolder(view : View) : RecyclerView.ViewHolder(view){
    private val binding  = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo){
        val context = binding.ivHoroscope.context
        binding.tvNombre.text = context.getString(horoscopeInfo.name)
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
    }
}