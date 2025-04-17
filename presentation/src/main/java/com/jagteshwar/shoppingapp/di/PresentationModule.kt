package com.jagteshwar.shoppingapp.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}