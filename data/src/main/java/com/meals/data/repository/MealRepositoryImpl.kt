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
        schoolDataSource.getSchool().flatMap { school ->
            mealDataSource.getMeal(school.school_id, school.office_code, date).map { mealList ->
                MealInfo(mealList[0], mealList[1], mealList[2])
            }
        }
}