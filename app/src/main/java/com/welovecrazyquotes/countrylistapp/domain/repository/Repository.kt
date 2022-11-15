package com.welovecrazyquotes.countrylistapp.domain.repository

import com.welovecrazyquotes.countrylistapp.common.Resource
import com.welovecrazyquotes.countrylistapp.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun allCountries(): Flow<Resource<List<Country>>>
    suspend fun getCountry(name: String): Flow<Resource<Country>>
}