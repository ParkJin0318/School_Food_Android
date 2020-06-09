package com.meals.school_food.di

import com.meals.data.network.service.MealService
import com.meals.data.network.service.ScheduleService
import com.meals.data.network.service.SearchService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single { (get() as Retrofit).create(MealService::class.java) }
    single { (get() as Retrofit).create(ScheduleService::class.java) }
    single { (get() as Retrofit).create(SearchService::class.java) }
}