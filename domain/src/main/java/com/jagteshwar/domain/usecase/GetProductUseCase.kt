package com.jagteshwar.domain.usecase

import com.jagteshwar.domain.repository.ProductRepository

class GetProductUseCase(
    private val productRepository: ProductRepository
) {

    suspend fun execute() = productRepository.getProduct()
}