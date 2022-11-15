package com.welovecrazyquotes.countrylistapp.domain.use_cases

import com.welovecrazyquotes.countrylistapp.domain.use_cases.all_countries.GetAllCountries
import com.welovecrazyquotes.countrylistapp.domain.use_cases.country_detail.GetCountryDetail

data class CountryUseCases (
    val allCountries: GetAllCountries,
    val countryDetail: GetCountryDetail
)