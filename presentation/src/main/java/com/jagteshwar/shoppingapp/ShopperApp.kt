package com.jagteshwar.shoppingapp

import android.app.Application
import com.jagteshwar.data.di.dataModule
import com.jagteshwar.domain.di.domainModule
import com.jagteshwar.shoppingapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShopperApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShopperApp)
            modules(
                listOf(
                    presentationModule,
                    domainModule,
                    dataModule
                )
            )
        }
    }
}