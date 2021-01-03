package com.meals.school_food.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetMealUseCase
import com.meals.domain.model.MealInfo
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.Event
import com.meals.school_food.widget.extension.getDateFormat
import com.meals.school_food.widget.extension.krDateFormat
import io.reactivex.observers.DisposableSingleObserver
import java.util.*

class MealViewModel(
    private val getMealUseCase: GetMealUseCase
) : BaseViewModel() {

    // View Binding LiveData
    val breakfastText = MutableLiveData<String>()
    val lunchText = MutableLiveData<String>()
    val dinnerText = MutableLiveData<String>()
    val dateText = MutableLiveData<String>()

    // ViewModel Logic LiveData
    private val _onDateChangeEvent = MutableLiveData<Event<Boolean>>()
    val onDateChangeEvent: LiveData<Event<Boolean>>
        get() = _onDateChangeEvent


    init {
        dateText.value = Date().krDateFormat()
        getMeal(dateText.value!!.getDateFormat())
    }

    private fun getMeal(date : String) {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(date)),
            object : DisposableSingleObserver<MealInfo>() {
                override fun onSuccess(t: MealInfo) {
                    breakfastText.value = t.breakfast
                    lunchText.value = t.lunch
                    dinnerText.value = t.dinner

                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    breakfastText.value = e.message
                    lunchText.value = e.message
                    dinnerText.value = e.message

                    isLoading.value = true
                }
            })
    }

    fun setDate(year : Int, month : Int, day : Int) {
        dateText.value = "${year}년 ${month}월 ${day}일"
        isLoading.value = false
        getMeal(dateText.value!!.getDateFormat())
    }

    fun onDateChangeClick() {
        _onDateChangeEvent.value = Event(true)
    }
}