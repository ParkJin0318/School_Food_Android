package com.meals.school_food.widget

import android.app.Application
import com.meals.data.util.SharedPreferenceManager
import com.meals.school_food.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            val modules = listOf(viewModelModule, netWorkModule, serviceModule,
                remoteModule, dataSourceModule, repositoryModule, useCaseModule)
            modules(modules)
        }
    }
}