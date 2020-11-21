package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetMealUseCase
import com.meals.domain.usecase.GetScheduleUseCase
import com.meals.domain.model.MealInfo
import com.meals.domain.model.ScheduleInfo
import com.meals.domain.model.SchoolInfo
import com.meals.domain.model.TimeInfo
import com.meals.domain.usecase.GetSchoolUseCase
import com.meals.school_food.R
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.extension.*
import com.meals.school_food.widget.recyclerview.adapter.ScheduleAdapter
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(
    private val getMealUseCase: GetMealUseCase,
    private val getScheduleUseCase: GetScheduleUseCase
) : BaseViewModel() {
    val mealText = MutableLiveData<String>()

    val time = MutableLiveData<String>()
    val timeInfo = MutableLiveData<TimeInfo>()

    val scheduleAdapter = ScheduleAdapter()
    private val scheduleList = ArrayList<ScheduleInfo>()

    val onScheduleDetailEvent = SingleLiveEvent<Unit>()
    val onMealDetailEvent = SingleLiveEvent<Unit>()
    val onErrorEvent = SingleLiveEvent<String>()

    init {
        scheduleAdapter.setList(scheduleList)
        getMeal()
        getSchedule()
    }

    private fun getMeal() {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(Date().dayDateFormat())),
            object : DisposableSingleObserver<MealInfo>() {
                override fun onSuccess(t: MealInfo) {
                    setCurrentMeal(t)
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    onErrorEvent.value = e.message
                    isLoading.value = true
                }
            })
    }

    private fun setCurrentMeal(mealInfo: MealInfo) {
        val now = Date().getFormatDate()
        when {
            now.before("08:20".getTime()) -> {
                mealText.value = mealInfo.breakfast
                timeInfo.value = TimeInfo.BREAKFAST
            }
            now.before("13:20".getTime()) -> {
                mealText.value = mealInfo.lunch
                timeInfo.value = TimeInfo.LUNCH
            }
            else -> {
                mealText.value = mealInfo.dinner
                timeInfo.value = TimeInfo.DINNER
            }
        }
    }

    private fun getSchedule() {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(Date().monthDateFormat())),
            object : DisposableSingleObserver<List<ScheduleInfo>>() {
                override fun onSuccess(t: List<ScheduleInfo>) {
                    scheduleList.clear()
                    scheduleList.addAll(t)
                    scheduleAdapter.notifyDataSetChanged()

                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    isLoading.value = true
                }
            })
    }

    fun onScheduleDetailClick() {
        onScheduleDetailEvent.call()
    }

    fun onMealDetailClick() {
        onMealDetailEvent.call()
    }
}