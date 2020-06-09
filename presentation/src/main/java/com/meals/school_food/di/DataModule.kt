package com.meals.school_food.di

import com.meals.data.dataSource.MealDataSource
import com.meals.data.dataSource.ScheduleDataSource
import com.meals.data.dataSource.SearchDataSource
import com.meals.data.network.remote.MealRemote
import com.meals.data.network.remote.ScheduleRemote
import com.meals.data.network.remote.SearchRemote
import com.meals.data.repository.MealRepositoryImpl
import com.meals.data.repository.ScheduleRepositoryImpl
import com.meals.data.repository.SearchRepositoryImpl
import com.meals.domain.dataSource.GetMealUseCase
import com.meals.domain.dataSource.GetScheduleUseCase
import com.meals.domain.dataSource.GetSearchUseCase
import com.meals.domain.repository.MealRepository
import com.meals.domain.repository.ScheduleRepository
import com.meals.domain.repository.SearchRepository
import org.koin.dsl.module

val remoteModule = module {
    single { MealRemote(get()) }
    single { ScheduleRemote(get()) }
    single { SearchRemote(get()) }
}

val dataSourceModule = module {
    single { MealDataSource(get()) }
    single { ScheduleDataSource(get()) }
    single { SearchDataSource(get()) }
}

val repositoryModule = module {
    single<MealRepository> { MealRepositoryImpl(get()) }
    single<ScheduleRepository> { ScheduleRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
}

val useCaseModule = module {
    single { GetMealUseCase(get()) }
    single { GetScheduleUseCase(get()) }
    single { GetSearchUseCase(get()) }
}