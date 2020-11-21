package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetMealUseCase
import com.meals.domain.usecase.GetScheduleUseCase
import com.meals.domain.model.MealInfo
import com.meals.domain.model.ScheduleInfo
import com.meals.domain.model.SchoolInfo
import com.meals.domain.usecase.GetSchoolUseCase
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.extension.dayDateFormat
import com.meals.school_food.widget.extension.krDateFormat
import com.meals.school_food.widget.extension.monthDateFormat
import com.meals.school_food.widget.recyclerview.adapter.ScheduleAdapter
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(
    private val getMealUseCase: GetMealUseCase,
    private val getScheduleUseCase: GetScheduleUseCase
) : BaseViewModel() {
    val mealText = MutableLiveData<String>()
    val mealInfo = MutableLiveData<MealInfo>()

    val time = MutableLiveData<String>()

    val scheduleAdapter = ScheduleAdapter()
    private val scheduleList = ArrayList<ScheduleInfo>()

    val onScheduleDetailEvent = SingleLiveEvent<Unit>()
    val onMealDetailEvent = SingleLiveEvent<Unit>()

    init {
        scheduleAdapter.setList(scheduleList)
        getMeal()
        getSchedule()
    }

    private fun getMeal() {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(Date().dayDateFormat())),
            object : DisposableSingleObserver<MealInfo>() {
                override fun onSuccess(t: MealInfo) {
                    mealInfo.value = t
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    isLoading.value = true
                }
            })
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