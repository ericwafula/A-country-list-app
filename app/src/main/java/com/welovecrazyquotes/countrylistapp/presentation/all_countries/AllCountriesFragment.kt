package com.welovecrazyquotes.countrylistapp.presentation.all_countries

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.welovecrazyquotes.countrylistapp.databinding.FragmentAllCountriesBinding
import com.welovecrazyquotes.countrylistapp.presentation.adapter.CountryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCountriesFragment : Fragment() {
    private lateinit var binding: FragmentAllCountriesBinding
    private val viewModel: AllCountriesFragmentViewModel by viewModels()
    private lateinit var countryAdapter: CountryAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        countryAdapter = CountryAdapter(requireContext()) { country ->
            val action = AllCountriesFragmentDirections.actionAllCountriesFragmentToCountryDetailFragment(
                country.name?.common!!.lowercase())
            findNavController().navigate(action)
        }
        binding.rcvCountries.apply {
            layoutManager = LinearLayoutManager(
                this@AllCountriesFragment.requireContext(),
                RecyclerView.VERTICAL,
                false
            )
            adapter = countryAdapter
        }
        lifecycleScope.launchWhenCreated {
            viewModel.allCountries.collect { event ->
                if (event.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.txvErrorMessage.text = ""
                }
                if (event.message?.isNotEmpty() == true) {
                    binding.txvErrorMessage.text = event.message
                    binding.progressBar.visibility = View.GONE
                }
                if (event.data != null) {
                    binding.progressBar.visibility = View.GONE
                    binding.txvErrorMessage.text = ""
                    lifecycleScope.launchWhenCreated {
                        countryAdapter.submitList(event.data)
                    }
                }
            }
        }
    }
}