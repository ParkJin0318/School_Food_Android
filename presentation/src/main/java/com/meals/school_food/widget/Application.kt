package com.meals.school_food.widget

import android.app.Application
import com.meals.school_food.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            val modules = listOf(viewModelModule)
            modules(modules)
        }
    }
}