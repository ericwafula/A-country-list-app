package com.welovecrazyquotes.countrylistapp.presentation.country_detail

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
class CountryDetailViewModel @Inject constructor(
    private val countryUseCases: CountryUseCases
) : ViewModel() {
    private var _countryDetails = MutableStateFlow(UiEvent<Country>())
    val countryDetails = _countryDetails.asStateFlow()

    fun getCountryDetails(name: String) {
        viewModelScope.launch {
            countryUseCases.countryDetail(name).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _countryDetails.value = UiEvent(data = result.data)
                    }
                    is Resource.Loading -> {
                        _countryDetails.value = UiEvent(isLoading = true)
                    }
                    is Resource.Error -> {
                        _countryDetails.value = UiEvent(message = result.message ?: "An unexpected error occured")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}