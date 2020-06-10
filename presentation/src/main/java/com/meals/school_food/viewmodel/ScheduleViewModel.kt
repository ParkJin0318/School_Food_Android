package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
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

    val string = MutableLiveData<String>()
    val check = MutableLiveData<Schedule>()

    val scheduleList = ArrayList<Schedule>()

    private fun getSchedule() {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params("7240393", "D10", "202006")),
            object : DisposableSingleObserver<Schedule>() {
                override fun onSuccess(t: Schedule) {
                    scheduleList.add(t)
                    check.value = t
                }
                override fun onError(e: Throwable) { }
            })
    }
}