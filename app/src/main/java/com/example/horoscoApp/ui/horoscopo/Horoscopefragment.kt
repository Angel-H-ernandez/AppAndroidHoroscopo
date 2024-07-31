package com.example.horoscoApp.ui.horoscopo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.horoscoApp.domain.model.HoroscopeInfo
import com.example.horoscoApp.domain.model.HoroscopeModel
import com.example.horoscoApp.ui.horoscopo.adapter.HoroscopeAdapter
import com.example.intermedioappkottlin.databinding.FragmentHoroscopefragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Horoscopefragment : Fragment() {
    private val horoscopeviewModel by viewModels<HoroscopoViewModel>()
    private var _binding : FragmentHoroscopefragmentBinding? = null
    private lateinit var Horoscope_adapter : HoroscopeAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHoroscopefragmentBinding.inflate(layoutInflater,container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
       initUIState()
    }

    private fun initList() {
        Horoscope_adapter = HoroscopeAdapter(onItemSelected = {
           val type = when (it){
                HoroscopeInfo.Aquarius -> HoroscopeModel.Aquarius
                HoroscopeInfo.Aries -> HoroscopeModel.Aries
                HoroscopeInfo.Cancer -> HoroscopeModel.Cancer
                HoroscopeInfo.Capricorn -> HoroscopeModel.Capricorn
                HoroscopeInfo.Gemini ->  HoroscopeModel.Gemini
                HoroscopeInfo.Leo ->  HoroscopeModel.Leo
                HoroscopeInfo.Libra ->  HoroscopeModel.Libra
                HoroscopeInfo.Pisces ->  HoroscopeModel.Pisces
                HoroscopeInfo.Sagittarius -> HoroscopeModel.Sagittarius
                HoroscopeInfo.Scorpio ->  HoroscopeModel.Scorpio
                HoroscopeInfo.Taurus ->  HoroscopeModel.Taurus
                HoroscopeInfo.Virgo -> HoroscopeModel.Virgo
            }
            findNavController().navigate(
                HoroscopefragmentDirections.actionHoroscopefragmentToHoroscopeDetailActivity(type)
            )
        })
        binding.rvHoroscope.apply{
            layoutManager = GridLayoutManager(context, 2)
            adapter = Horoscope_adapter
        }

    }

    private fun initUIState() {
        //esto es una corrutina qespecil ue se enngancha al ciclo de vida del fragment
       lifecycleScope.launch {
           repeatOnLifecycle(Lifecycle.State.STARTED){
               horoscopeviewModel.horoscopo.collect{
                   Log.i("LISRTff", it.toString())
                   Horoscope_adapter.updateList(it)


               }

           }
       }
    }

}