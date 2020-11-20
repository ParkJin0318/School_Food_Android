package com.meals.data.network.service

import com.meals.data.network.response.Response
import com.meals.domain.model.Meal
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {
    @GET("meals")
    fun getMeals(@Query("school_id", encoded = true) id: String,
                 @Query("office_code", encoded = true) code: String?,
                 @Query("date", encoded = true) date: String?) : Single<retrofit2.Response<Response<Meal>>>
}