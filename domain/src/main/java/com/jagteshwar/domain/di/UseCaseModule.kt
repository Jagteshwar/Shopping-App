package com.jagteshwar.domain.di

import com.jagteshwar.domain.usecase.GetCategoryUseCase
import com.jagteshwar.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductUseCase(get()) }
    factory { GetCategoryUseCase(get()) }
}