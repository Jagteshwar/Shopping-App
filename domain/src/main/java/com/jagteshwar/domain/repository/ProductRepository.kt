package com.jagteshwar.domain.repository

import com.jagteshwar.domain.model.Product
import com.jagteshwar.domain.network.ResultWrapper

interface ProductRepository {

    suspend fun getProduct(): ResultWrapper<List<Product>>
}