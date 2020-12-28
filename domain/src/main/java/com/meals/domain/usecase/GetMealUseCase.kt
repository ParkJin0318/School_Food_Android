package com.meals.domain.usecase

import com.meals.domain.base.ParamsUseCase
import com.meals.domain.model.MealInfo
import com.meals.domain.repository.MealRepository
import io.reactivex.Single

class GetMealUseCase(
    private val mealRepository: MealRepository
) : ParamsUseCase<GetMealUseCase.Params, Single<MealInfo>>() {

    override fun buildUseCaseObservable(params: Params): Single<MealInfo> =
        mealRepository.getMeal(params.date)

    data class Params(
        val date: String
    )
}
