package com.welovecrazyquotes.countrylistapp.presentation.all_countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.welovecrazyquotes.countrylistapp.databinding.FragmentAllCountriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCountriesFragment : Fragment() {
    private lateinit var binding: FragmentAllCountriesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }
}