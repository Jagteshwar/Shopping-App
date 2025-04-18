package com.jagteshwar.domain.repository

import com.jagteshwar.domain.network.ResultWrapper

interface CategoryRepository {

    suspend fun getCategories(): ResultWrapper<List<String>>
}