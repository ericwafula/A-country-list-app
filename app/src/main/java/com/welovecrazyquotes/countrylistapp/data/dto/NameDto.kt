package com.welovecrazyquotes.countrylistapp.data.dto

import com.welovecrazyquotes.countrylistapp.domain.model.Name

data class NameDto(
    val common: String,
    val official: String
)

fun NameDto.toName(): Name {
    return Name(
        common = common,
        official = official
    )
}