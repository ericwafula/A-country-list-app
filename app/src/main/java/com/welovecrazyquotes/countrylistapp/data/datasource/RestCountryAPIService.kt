package com.welovecrazyquotes.countrylistapp.data.datasource

import com.welovecrazyquotes.countrylistapp.data.dto.CountryDto
import retrofit2.http.GET

interface RestCountryAPIService {
    @GET("all")
    suspend fun getAllCountries(): List<CountryDto>
}
