package com.jagteshwar.data.repository

import com.jagteshwar.domain.model.Product
import com.jagteshwar.domain.network.NetworkService
import com.jagteshwar.domain.network.ResultWrapper
import com.jagteshwar.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val networkService: NetworkService
): ProductRepository {
    override suspend fun getProduct(): ResultWrapper<List<Product>> {
        return networkService.getProducts()
    }
}