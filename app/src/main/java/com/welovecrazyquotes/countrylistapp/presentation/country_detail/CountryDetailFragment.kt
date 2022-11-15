package com.welovecrazyquotes.countrylistapp.presentation.country_detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.welovecrazyquotes.countrylistapp.databinding.FragmentCountryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailFragment: Fragment() {
    private lateinit var binding: FragmentCountryDetailBinding
    private val args: CountryDetailFragmentArgs by navArgs()
    private val countryDetailViewModel: CountryDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDetailBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        Log.d("TAG", "initViews: ${args.countryName}")
        lifecycleScope.launchWhenCreated {
            countryDetailViewModel.getCountryDetails(args.countryName)
            countryDetailViewModel.countryDetails.collect { event ->
                if (event.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.countryDetails.visibility = View.GONE
                    binding.txvStatus.text = ""
                }
                if (event.message?.isNotEmpty() == true) {
                    binding.progressBar.visibility = View.GONE
                    binding.txvStatus.text = event.message
                }
                if (event.data != null) {
                    binding.progressBar.visibility = View.GONE
                    binding.txvStatus.text = ""
                    event.data.also { country ->
                        binding.apply {
                            Glide.with(requireContext())
                                .load(country?.flags?.svg)
                                .into(imvFlag)
                            tvLangs.text = country?.languages.toString()
                            tvCapital.text = country?.capital?.get(0)
                            tvOname.text = country?.name?.official
                            tvPop.text = country?.population.toString()
                            tvRegion.text = country?.region
                            tvSregion.text = country?.subregion
                        }
                    }
                }
            }
        }
    }
}