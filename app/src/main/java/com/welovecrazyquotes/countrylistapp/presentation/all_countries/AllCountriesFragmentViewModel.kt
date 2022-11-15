package com.welovecrazyquotes.countrylistapp.presentation.all_countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welovecrazyquotes.countrylistapp.common.Resource
import com.welovecrazyquotes.countrylistapp.domain.model.Country
import com.welovecrazyquotes.countrylistapp.domain.use_cases.CountryUseCases
import com.welovecrazyquotes.countrylistapp.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCountriesFragmentViewModel @Inject constructor(
    private val countryUseCases: CountryUseCases
): ViewModel() {
    private var _allCountries = MutableStateFlow(UiEvent<List<Country>>())
    val allCountries = _allCountries.asStateFlow()

    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch {
            countryUseCases.allCountries().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _allCountries.value = UiEvent(data = result.data)
                    }
                    is Resource.Loading -> {
                        _allCountries.value = UiEvent(isLoading = true)
                    }
                    is Resource.Error -> {
                        _allCountries.value = UiEvent(message = result.message ?: "An unexpected error occurred")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}