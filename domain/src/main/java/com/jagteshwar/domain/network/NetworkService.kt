package com.jagteshwar.domain.network

import com.jagteshwar.domain.model.Product

interface NetworkService {
    suspend fun getProducts(): ResultWrapper<List<Product>>
}


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Failure(val exception: Exception): ResultWrapper<Nothing>()
}