package com.meals.school_food.di

import com.meals.school_food.viewmodel.AddressViewModel
import com.meals.school_food.viewmodel.MealViewModel
import com.meals.school_food.viewmodel.ScheduleViewModel
import com.meals.school_food.viewmodel.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MealViewModel(androidApplication(), get()) }
    viewModel { AddressViewModel(get()) }
    viewModel { ScheduleViewModel(get()) }
    viewModel { SearchViewModel() }
}