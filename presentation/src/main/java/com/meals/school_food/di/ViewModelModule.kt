package com.meals.school_food.di

import com.meals.school_food.viewmodel.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(androidApplication(), get(), get()) }
    viewModel { MealViewModel() }
    viewModel { ScheduleViewModel(get()) }
    viewModel { MenuViewModel() }
    viewModel { SearchViewModel(get()) }
}