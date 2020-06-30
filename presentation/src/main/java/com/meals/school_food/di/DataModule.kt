package com.meals.school_food.di

import com.meals.data.dataSource.MealDataSource
import com.meals.data.dataSource.ScheduleDataSource
import com.meals.data.dataSource.SchoolDataSource
import com.meals.data.network.remote.MealRemote
import com.meals.data.network.remote.ScheduleRemote
import com.meals.data.network.remote.SchoolRemote
import com.meals.data.repository.MealRepositoryImpl
import com.meals.data.repository.ScheduleRepositoryImpl
import com.meals.data.repository.SchoolRepositoryImpl
import com.meals.domain.dataSource.GetMealUseCase
import com.meals.domain.dataSource.GetScheduleUseCase
import com.meals.domain.dataSource.GetSchoolUseCase
import com.meals.domain.repository.MealRepository
import com.meals.domain.repository.ScheduleRepository
import com.meals.domain.repository.SchoolRepository
import org.koin.dsl.module

val remoteModule = module {
    single { MealRemote(get()) }
    single { ScheduleRemote(get()) }
    single { SchoolRemote(get()) }
}

val dataSourceModule = module {
    single { MealDataSource(get()) }
    single { ScheduleDataSource(get()) }
    single { SchoolDataSource(get()) }
}

val repositoryModule = module {
    single<MealRepository> { MealRepositoryImpl(get()) }
    single<ScheduleRepository> { ScheduleRepositoryImpl(get()) }
    single<SchoolRepository> { SchoolRepositoryImpl(get()) }
}

val useCaseModule = module {
    single { GetMealUseCase(get()) }
    single { GetScheduleUseCase(get()) }
    single { GetSchoolUseCase(get()) }
}