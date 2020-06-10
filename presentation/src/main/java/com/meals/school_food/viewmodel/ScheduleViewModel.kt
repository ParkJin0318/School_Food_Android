package com.meals.school_food.viewmodel

import com.meals.domain.dataSource.GetScheduleUseCase
import com.meals.domain.model.Schedule
import com.meals.school_food.base.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver

class ScheduleViewModel(
    private val getScheduleUseCase: GetScheduleUseCase
) : BaseViewModel() {

    init {
        getSchedule()
    }

    val scheduleList = ArrayList<Schedule>()

    private fun getSchedule() {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params("7240393", "D10", "202006")),
            object : DisposableSingleObserver<Schedule>() {
                override fun onSuccess(t: Schedule) {
                    scheduleList.add(t)
                }
                override fun onError(e: Throwable) { }
            })
    }
}