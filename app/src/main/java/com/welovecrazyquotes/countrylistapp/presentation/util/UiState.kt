package com.welovecrazyquotes.countrylistapp.presentation.util

data class UiEvent<T>(
    var data: T? = null,
    var isLoading: Boolean = false,
    var message: String? = ""
)