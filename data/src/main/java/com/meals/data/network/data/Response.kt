package com.meals.data.network.data

data class Response<T>(
    val status: Int,
    val message: String,
    val data: T
)