package com.welovecrazyquotes.countrylistapp.data.dto

import com.welovecrazyquotes.countrylistapp.domain.model.Currency

data class CurrencyDto(
    val name: String?,
    val symbol: String?
)

fun CurrencyDto.toCurrency(): Currency {
    return Currency(
        name = name,
        symbol = symbol
    )
}