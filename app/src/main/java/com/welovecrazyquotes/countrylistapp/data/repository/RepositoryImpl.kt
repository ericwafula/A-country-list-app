package com.welovecrazyquotes.countrylistapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.welovecrazyquotes.countrylistapp.common.Resource
import com.welovecrazyquotes.countrylistapp.data.datasource.RestCountryAPIService
import com.welovecrazyquotes.countrylistapp.data.dto.toCountry
import com.welovecrazyquotes.countrylistapp.domain.model.Country
import com.welovecrazyquotes.countrylistapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: RestCountryAPIService
) : Repository {
    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun allCountries(): Flow<Resource<List<Country>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val countries = apiService.getAllCountries().map { countryDto ->
                    countryDto.toCountry()
                }
                emit(Resource.Success(countries))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage
                            ?: "Unable to connect to the server. Please check your network connection"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error("An unexpected error occured"))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun getCountry(name: String): Flow<Resource<Country>> {
        return flow {
            try {
                emit(Resource.Loading())
                val countries = apiService.getAllCountries().map { countryDto ->
                    countryDto.toCountry()
                }
                countries.find { country -> country.name.common == name }?.let {
                    emit(Resource.Success(it))
                }
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage
                            ?: "Unable to connect to the server. Please check your network connection"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error("An unexpected error occured"))
            }
        }
    }
}