package com.welovecrazyquotes.countrylistapp.common

sealed class Resource<T>(data: T?, message: String?) {
    class Success<T>(data: T): Resource<T>(data, null)
    class Error<T>(message: String): Resource<T>(null, message)
    class Loading<T>: Resource<T>(null, null)
}
