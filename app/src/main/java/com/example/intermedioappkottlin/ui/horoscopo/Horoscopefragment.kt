package com.example.intermedioappkottlin.ui.horoscopo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.intermedioappkottlin.R
import com.example.intermedioappkottlin.databinding.FragmentHoroscopefragmentBinding


class Horoscopefragment : Fragment() {
    private var _binding : FragmentHoroscopefragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHoroscopefragmentBinding.inflate(layoutInflater,container, false)

        return binding.root

    }

}