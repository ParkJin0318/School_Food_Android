package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetMealUseCase
import com.meals.domain.model.MealInfo
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.extension.getDateFormat
import com.meals.school_food.widget.extension.krDateFormat
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import kotlin.collections.ArrayList

class MealViewModel(
    private val getMealUseCase: GetMealUseCase
) : BaseViewModel() {
    val schoolName = MutableLiveData<String>()

    val breakfast = MutableLiveData<String>()
    val lunch = MutableLiveData<String>()
    val dinner = MutableLiveData<String>()

    val date = MutableLiveData<String>()
    val mealCheck = MutableLiveData<String>()
    val dateEvent = SingleLiveEvent<Unit>()

    init {
        date.value = Date().krDateFormat()
        getMeal(date.value.toString().getDateFormat())
    }

    private fun getMeal(date : String) {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(date)),
            object : DisposableSingleObserver<MealInfo>() {
                override fun onSuccess(t: MealInfo) {
                    breakfast.value = t.breakfast
                    lunch.value = t.lunch
                    dinner.value = t.dinner

                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    mealCheck.value = "급식이 없습니다"
                    isLoading.value = true
                }
            })
    }

    fun setDate(year : Int, month : Int, day : Int) {
        date.value = "${year}년 ${month}월 ${day}일"
        getMeal(date.value.toString().getDateFormat())
        isLoading.value = false
        mealCheck.value = null
    }

    fun dateClick() {
        dateEvent.call()
    }
}