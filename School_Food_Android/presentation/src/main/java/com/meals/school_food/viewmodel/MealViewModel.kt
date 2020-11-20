package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetMealUseCase
import com.meals.domain.model.MealInfo
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.extension.getDateFormat
import com.meals.school_food.widget.extension.krDateFormat
import com.meals.school_food.widget.recyclerview.adapter.MealAdapter
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import kotlin.collections.ArrayList

class MealViewModel(
    private val getMealUseCase: GetMealUseCase
) : BaseViewModel() {
    val schoolName = MutableLiveData<String>()

    val date = MutableLiveData<String>()
    val mealCheck = MutableLiveData<String>()
    val dateEvent = SingleLiveEvent<Unit>()

    val morningAdapter = MealAdapter()
    val lunchAdapter = MealAdapter()
    val dinnerAdapter = MealAdapter()

    private val morningList = ArrayList<String>()
    private val lunchList = ArrayList<String>()
    private val dinnerList = ArrayList<String>()

    init {
        morningAdapter.setList(morningList)
        lunchAdapter.setList(lunchList)
        dinnerAdapter.setList(dinnerList)

        date.value = Date().krDateFormat()
        getMeal(date.value.toString().getDateFormat())
    }

    private fun getMeal(date : String) {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(date)),
            object : DisposableSingleObserver<MealInfo>() {
                override fun onSuccess(t: MealInfo) {
                    addMealData(t)
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    mealCheck.value = "급식이 없습니다"
                    isLoading.value = true
                }
            })
    }

    private fun addMealData(t: MealInfo) {
        clearMeal()

        morningList.addAll(t.breakfast!!.split("<br/>"))
        lunchList.addAll(t.lunch!!.split("<br/>"))
        dinnerList.addAll(t.dinner!!.split("<br/>"))

        changeMeal()
    }

    fun setDate(year : Int, month : Int, day : Int) {
        date.value = "${year}년 ${month}월 ${day}일"
        getMeal(date.value.toString().getDateFormat())
        clearMeal()
        changeMeal()
        isLoading.value = false
        mealCheck.value = null
    }
    
    private fun clearMeal() {
        morningList.clear()
        lunchList.clear()
        dinnerList.clear()
    }

    private fun changeMeal() {
        morningAdapter.notifyDataSetChanged()
        lunchAdapter.notifyDataSetChanged()
        dinnerAdapter.notifyDataSetChanged()
    }

    fun dateClick() {
        dateEvent.call()
    }
}