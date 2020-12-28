package com.meals.school_food.di

import com.meals.school_food.viewmodel.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { MealViewModel(get()) }
    viewModel { ScheduleViewModel(get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { VersionViewModel(androidApplication()) }
}