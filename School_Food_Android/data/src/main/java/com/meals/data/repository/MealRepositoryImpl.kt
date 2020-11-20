package com.meals.data.repository

import com.meals.data.datasource.MealDataSource
import com.meals.data.datasource.SchoolDataSource
import com.meals.data.mapper.mapToModel
import com.meals.domain.model.MealInfo
import com.meals.domain.repository.MealRepository
import io.reactivex.Single

class MealRepositoryImpl(
    private val mealDataSource: MealDataSource,
    private val schoolDataSource: SchoolDataSource
): MealRepository {

    override fun getMeal(date: String): Single<MealInfo> =
        schoolDataSource.getSchool().flatMap {
            mealDataSource.getMeal(it.school_id, it.office_code, date).map { mealList ->
                val meal = mealList.meals
                return@map MealInfo(meal[0], meal[1], meal[2])
            }
        }
}