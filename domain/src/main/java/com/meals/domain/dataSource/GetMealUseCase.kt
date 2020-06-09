package com.meals.domain.dataSource

import com.meals.domain.base.ParamsUseCase
import com.meals.domain.model.Meal
import com.meals.domain.repository.MealRepository
import io.reactivex.Single

class GetMealUseCase(
    private val mealRepository: MealRepository
) : ParamsUseCase<GetMealUseCase.Params, Single<Meal>>() {

    override fun buildUseCaseObservable(params: Params): Single<Meal> =
        mealRepository.getMeal(params.id, params.code, params.date)

    data class Params(
        val id: String,
        val code: String,
        val date: String
    )
}
