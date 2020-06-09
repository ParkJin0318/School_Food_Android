package com.meals.data.repository

import com.meals.data.dataSource.MealDataSource
import com.meals.domain.model.Meal
import com.meals.domain.repository.MealRepository
import io.reactivex.Single

class MealRepositoryImpl(private val mealDataSource: MealDataSource): MealRepository {

    override fun getMeal(id: String, code: String, date: String): Single<Meal> =
        mealDataSource.getMeal(id, code, date)
}