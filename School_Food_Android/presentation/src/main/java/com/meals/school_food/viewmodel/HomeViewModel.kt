package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetMealUseCase
import com.meals.domain.usecase.GetScheduleUseCase
import com.meals.domain.model.MealInfo
import com.meals.domain.model.ScheduleInfo
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.extension.dayDateFormat
import com.meals.school_food.widget.extension.krDateFormat
import com.meals.school_food.widget.extension.monthDateFormat
import com.meals.school_food.widget.recyclerview.adapter.MealAdapter
import com.meals.school_food.widget.recyclerview.adapter.ScheduleAdapter
import io.reactivex.Observable
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(
    private val getMealUseCase: GetMealUseCase,
    private val getScheduleUseCase: GetScheduleUseCase
) : BaseViewModel() {

    val schoolName = MutableLiveData<String>()

    val time = MutableLiveData<String>("아침")
    val date = MutableLiveData<String>()
    val mealCheck = MutableLiveData<String>()
    val scheduleCheck = MutableLiveData<String>()

    val searchEvent = SingleLiveEvent<Unit>()

    val isMorning = MutableLiveData(false)
    val isLunch = MutableLiveData(false)
    val isDinner = MutableLiveData(false)

    val morningAdapter = MealAdapter()
    val lunchAdapter = MealAdapter()
    val dinnerAdapter = MealAdapter()
    val scheduleAdapter = ScheduleAdapter()

    private val morningList = ArrayList<String>()
    private val lunchList = ArrayList<String>()
    private val dinnerList = ArrayList<String>()
    private val scheduleList = ArrayList<ScheduleInfo>()

    init {
        morningAdapter.setList(morningList)
        lunchAdapter.setList(lunchList)
        dinnerAdapter.setList(dinnerList)
        scheduleAdapter.setList(scheduleList)

        date.value = Date().krDateFormat()
        getMeal()
        getSchedule()
        isMorning.value = true
    }

    private fun getMeal() {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(Date().dayDateFormat())),
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

    private fun getSchedule() {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(Date().monthDateFormat())),
            object : DisposableSingleObserver<List<ScheduleInfo>>() {
                override fun onSuccess(t: List<ScheduleInfo>) {
                    addScheduleData(t)
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    scheduleCheck.value = "학사일정이 없습니다"
                    isLoading.value = true
                }
            })
    }

    private fun addMealData(t: MealInfo) {
        morningList.clear()
        lunchList.clear()
        dinnerList.clear()

        morningList.addAll(t.breakfast!!.split("<br/>"))
        lunchList.addAll(t.lunch!!.split("<br/>"))
        dinnerList.addAll(t.dinner!!.split("<br/>"))

        morningAdapter.notifyDataSetChanged()
        lunchAdapter.notifyDataSetChanged()
        dinnerAdapter.notifyDataSetChanged()
    }

    private fun addScheduleData(t: List<ScheduleInfo>) {
        scheduleList.clear()
        t.forEach {
            scheduleList.add(it)
        }
        scheduleAdapter.notifyDataSetChanged()
    }

    fun onCheckedBreakfast() {
        this.isMorning.value = true
        this.isLunch.value = false
        this.isDinner.value = false
    }

    fun onCheckedLunch() {
        this.isMorning.value = false
        this.isLunch.value = true
        this.isDinner.value = false
    }

    fun onCheckedDinner() {
        this.isMorning.value = false
        this.isLunch.value = false
        this.isDinner.value = true
    }

    fun searchClick() {
        searchEvent.call()
    }
}