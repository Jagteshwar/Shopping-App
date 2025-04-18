package com.jagteshwar.domain.usecase

import com.jagteshwar.domain.repository.CategoryRepository

class GetCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {

    suspend fun execute() = categoryRepository.getCategories()
}