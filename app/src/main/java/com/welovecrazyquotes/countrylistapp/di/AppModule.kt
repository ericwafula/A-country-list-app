package com.welovecrazyquotes.countrylistapp.di

import com.welovecrazyquotes.countrylistapp.common.Constants
import com.welovecrazyquotes.countrylistapp.data.datasource.RestCountryAPIService
import com.welovecrazyquotes.countrylistapp.domain.repository.Repository
import com.welovecrazyquotes.countrylistapp.domain.use_cases.CountryUseCases
import com.welovecrazyquotes.countrylistapp.domain.use_cases.all_countries.GetAllCountries
import com.welovecrazyquotes.countrylistapp.domain.use_cases.country_detail.GetCountryDetail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRestCountryApiService(retrofit: Retrofit): RestCountryAPIService =
        retrofit.create(RestCountryAPIService::class.java)


    @Provides
    @Singleton
    fun provideCountryUseCases(repository: Repository): CountryUseCases {
        return CountryUseCases(
            allCountries = GetAllCountries(repository = repository),
            countryDetail = GetCountryDetail(repository = repository)
        )
    }
}