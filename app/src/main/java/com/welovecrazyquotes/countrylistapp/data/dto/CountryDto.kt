package com.welovecrazyquotes.countrylistapp.data.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.welovecrazyquotes.countrylistapp.domain.model.Country
import com.welovecrazyquotes.countrylistapp.domain.model.Currency


data class CountryDto(
    val name: NameDto?,
    val currencies: Map<String, CurrencyDto>?,
    val capital: List<String>?,
    val region: String?,
    val subregion: String?,
    val languages: Map<String, String>?,
    val population: Int?,
    val flags: FlagDto?
)

@RequiresApi(Build.VERSION_CODES.N)
fun CountryDto.toCountry(): Country {
    return Country(
        name = name?.toName(),
        currencies = currencies?.toCurrencies(),
        capital = capital,
        region = region,
        subregion = subregion,
        languages = languages,
        population = population,
        flags = flags?.toFlag()
    )
}

@RequiresApi(Build.VERSION_CODES.N)
fun Map<String, CurrencyDto>.toCurrencies(): Map<String, Currency> {
    val currencies = mutableMapOf<String, Currency>()
    this.forEach { (key, value) ->
        currencies[key] = value.toCurrency()
    }
    return currencies
}