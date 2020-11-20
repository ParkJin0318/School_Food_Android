package com.meals.domain.repository

import com.meals.domain.model.MealInfo
import io.reactivex.Single

interface MealRepository {
    fun getMeal(date: String): Single<MealInfo>
}