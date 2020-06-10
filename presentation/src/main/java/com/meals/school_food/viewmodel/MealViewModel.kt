package com.meals.school_food.viewmodel

import com.meals.domain.dataSource.GetMealUseCase
import com.meals.domain.model.Meal
import com.meals.school_food.base.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver

class MealViewModel(
    private val getMealUseCase: GetMealUseCase
) : BaseViewModel() {

    init {
        getMeal()
    }

    val mealList = ArrayList<Meal>()

    private fun getMeal() {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params("7240393", "D10", "20200608")),
            object : DisposableSingleObserver<Meal>() {
                override fun onSuccess(t: Meal) {
                    mealList.add(t)
                }
                override fun onError(e: Throwable) { }
            })
    }
}