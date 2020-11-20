package com.meals.data.network.remote

import com.meals.data.network.service.MealService
import io.reactivex.Single
import com.meals.data.base.BaseRemote
import com.meals.data.network.response.MealData

class MealRemote(override val service: MealService): BaseRemote<MealService>() {

    fun getMeal(id: String, code: String, date: String): Single<MealData> =
        service.getMeals(id, code, date).map(getResponseData())
}