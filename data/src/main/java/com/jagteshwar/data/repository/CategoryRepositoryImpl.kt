package com.jagteshwar.data.repository

import com.jagteshwar.domain.network.NetworkService
import com.jagteshwar.domain.network.ResultWrapper
import com.jagteshwar.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val networkService: NetworkService
): CategoryRepository {
    override suspend fun getCategories(): ResultWrapper<List<String>> {
        return networkService.getCategories()
    }
}