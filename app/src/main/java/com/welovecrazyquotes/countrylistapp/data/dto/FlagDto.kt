package com.welovecrazyquotes.countrylistapp.data.dto

import com.welovecrazyquotes.countrylistapp.domain.model.Flag

data class FlagDto(
    val png: String,
    val svg: String
)

fun FlagDto.toFlag(): Flag {
    return Flag(
        png = png,
        svg = svg
    )
}