package com.example.horoscoApp.ui.horoscopo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
        Horoscope_adapter = HoroscopeAdapter()
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