package com.welovecrazyquotes.countrylistapp.domain.use_cases.country_detail

import com.welovecrazyquotes.countrylistapp.common.Resource
import com.welovecrazyquotes.countrylistapp.domain.model.Country
import com.welovecrazyquotes.countrylistapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountryDetail @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(name: String): Flow<Resource<Country>> {
        return repository.getCountry(name)
    }
}