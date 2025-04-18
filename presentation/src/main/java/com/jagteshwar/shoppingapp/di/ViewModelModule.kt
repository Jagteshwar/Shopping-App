package com.jagteshwar.shoppingapp.di

import com.jagteshwar.shoppingapp.ui.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
}