package com.welovecrazyquotes.countrylistapp.domain.model

data class Country(
    val name: Name,
    val currencies: Map<String, Currency>,
    val capital: List<String>,
    val region: String,
    val subregion: String,
    val languages: Map<String, String>,
    val population: Int,
    val flags: Flag
)