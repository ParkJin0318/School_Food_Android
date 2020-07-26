package com.meals.domain.repository

import com.meals.domain.model.Meal
import io.reactivex.Single

interface MealRepository {
    fun getMeal(id: String, code: String, date: String): Single<Meal>
}