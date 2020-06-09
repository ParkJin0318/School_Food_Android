package com.meals.school_food.viewmodel

import android.util.Log
import com.meals.domain.dataSource.GetMealUseCase
import com.meals.domain.dataSource.GetScheduleUseCase
import com.meals.domain.dataSource.GetSearchUseCase
import com.meals.domain.model.Meal
import com.meals.domain.model.Schedule
import com.meals.domain.model.Search
import com.meals.school_food.base.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver

class MainViewModel(
    private val getMealUseCase: GetMealUseCase,
    private val getScheduleUseCase: GetScheduleUseCase,
    private val getSearchUseCase: GetSearchUseCase
) : BaseViewModel() {

    init {
        getMeal()
        getSchedule()
        getSchools()
    }

    val mealList = ArrayList<Meal>()
    val scheduleList = ArrayList<Schedule>()
    val schoolList = ArrayList<Search>()

    private fun getMeal() {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params("7240393", "D10", "20200608")),
            object : DisposableSingleObserver<Meal>() {
                override fun onSuccess(t: Meal) {
                    mealList.add(t)
                    Log.e("parkjin", mealList[0].meals[0])
                }
                override fun onError(e: Throwable) { }
            })
    }

    private fun getSchedule() {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params("7240393", "D10", "202006")),
            object : DisposableSingleObserver<Schedule>() {
                override fun onSuccess(t: Schedule) {
                    scheduleList.add(t)
                    Log.e("parkjin", scheduleList[0].schedules[0].name)
                }
                override fun onError(e: Throwable) { }
            })
    }

    private fun getSchools() {
        addDisposable(getSearchUseCase.buildUseCaseObservable(GetSearchUseCase.Params("대구")),
            object : DisposableSingleObserver<Search>() {
                override fun onSuccess(t: Search) {
                    schoolList.add(t)
                    Log.e("parkjin", schoolList[0].schools[0].school_name)
                }
                override fun onError(e: Throwable) { }
            })
    }
}