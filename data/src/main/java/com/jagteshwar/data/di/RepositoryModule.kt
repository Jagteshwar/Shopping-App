package com.jagteshwar.data.di

import com.jagteshwar.data.repository.CategoryRepositoryImpl
import com.jagteshwar.data.repository.ProductRepositoryImpl
import com.jagteshwar.domain.repository.CategoryRepository
import com.jagteshwar.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> {ProductRepositoryImpl(get())}
    single<CategoryRepository> {CategoryRepositoryImpl(get()) }
}