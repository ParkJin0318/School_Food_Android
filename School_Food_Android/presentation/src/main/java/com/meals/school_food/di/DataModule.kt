package com.meals.school_food.di

import com.meals.data.database.cache.SchoolCache
import com.meals.data.datasource.MealDataSource
import com.meals.data.datasource.ScheduleDataSource
import com.meals.data.datasource.SchoolDataSource
import com.meals.data.network.remote.MealRemote
import com.meals.data.network.remote.ScheduleRemote
import com.meals.data.network.remote.SchoolRemote
import com.meals.data.repository.MealRepositoryImpl
import com.meals.data.repository.ScheduleRepositoryImpl
import com.meals.data.repository.SchoolRepositoryImpl
import com.meals.domain.usecase.GetMealUseCase
import com.meals.domain.usecase.GetScheduleUseCase
import com.meals.domain.usecase.GetSchoolUseCase
import com.meals.domain.repository.MealRepository
import com.meals.domain.repository.ScheduleRepository
import com.meals.domain.repository.SchoolRepository
import com.meals.domain.usecase.InsertSchoolUseCase
import org.koin.dsl.module

val remoteModule = module {
    single { MealRemote(get()) }
    single { ScheduleRemote(get()) }
    single { SchoolRemote(get()) }
}

val cacheModule = module {
    single { SchoolCache(get()) }
}

val dataSourceModule = module {
    single { MealDataSource(get(), Any()) }
    single { ScheduleDataSource(get(), Any()) }
    single { SchoolDataSource(get(), get()) }
}

val repositoryModule = module {
    single<MealRepository> { MealRepositoryImpl(get(), get()) }
    single<ScheduleRepository> { ScheduleRepositoryImpl(get(), get()) }
    single<SchoolRepository> { SchoolRepositoryImpl(get()) }
}

val useCaseModule = module {
    single { GetMealUseCase(get()) }
    single { GetScheduleUseCase(get()) }
    single { GetSchoolUseCase(get()) }
    single { InsertSchoolUseCase(get()) }
}