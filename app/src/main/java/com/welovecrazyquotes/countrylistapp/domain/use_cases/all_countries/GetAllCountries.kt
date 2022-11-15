package com.welovecrazyquotes.countrylistapp.domain.use_cases.all_countries

import com.welovecrazyquotes.countrylistapp.common.Resource
import com.welovecrazyquotes.countrylistapp.domain.model.Country
import com.welovecrazyquotes.countrylistapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCountries @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() : Flow<Resource<List<Country>>> {
        return repository.allCountries()
    }
}