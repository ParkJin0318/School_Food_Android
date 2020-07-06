package com.meals.school_food.di

import com.meals.school_food.viewmodel.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(androidApplication(), get(), get()) }
    viewModel { MealViewModel(androidApplication(), get()) }
    viewModel { ScheduleViewModel(androidApplication(), get()) }
    viewModel { MenuViewModel(androidApplication()) }
    viewModel { SearchViewModel(get()) }
    viewModel { VersionViewModel(androidApplication()) }
    viewModel { OpenSourceViewModel() }
}