package com.meals.data.repository

import com.meals.data.datasource.MealDataSource
import com.meals.data.datasource.SchoolDataSource
import com.meals.domain.model.Meal
import com.meals.domain.repository.MealRepository
import com.meals.domain.repository.SchoolRepository
import io.reactivex.Single

class MealRepositoryImpl(
    private val mealDataSource: MealDataSource,
    private val schoolDataSource: SchoolDataSource
): MealRepository {

    override fun getMeal(date: String): Single<Meal> =
        schoolDataSource.getSchool().flatMap {
            mealDataSource.getMeal(it.school_id, it.office_code, date)
        }
}