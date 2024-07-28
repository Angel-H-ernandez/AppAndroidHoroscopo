package com.example.horoscoApp.ui.horoscopo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscoApp.domain.model.HoroscopeInfo
import com.example.intermedioappkottlin.R

class HoroscopeAdapter(private var horosopeList: List<HoroscopeInfo> = emptyList(),
    private val onItemSelected: ((HoroscopeInfo) -> Unit)) :
    RecyclerView.Adapter<HoroscopeViewHolder>(){

        fun updateList(list:List<HoroscopeInfo>){
            horosopeList = list
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int    ): HoroscopeViewHolder{
            return HoroscopeViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)

            )
        }

        override fun getItemCount() = horosopeList.size

        override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
            holder.render(horosopeList[position], onItemSelected)
        }


}