package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetScheduleUseCase
import com.meals.domain.model.ScheduleInfo
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.extension.dayDateFormat
import com.meals.school_food.widget.recyclerview.adapter.ScheduleAdapter
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import kotlin.collections.ArrayList

class ScheduleViewModel(
    private val getScheduleUseCase: GetScheduleUseCase
) : BaseViewModel() {
    val information = MutableLiveData<String>()

    private val scheduleList = ArrayList<ScheduleInfo>()
    val scheduleAdapter = ScheduleAdapter()

    init {
        scheduleAdapter.setList(scheduleList)
        getSchedule(Date().dayDateFormat())
    }

    private fun getSchedule(date : String) {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(date)),
            object : DisposableSingleObserver<List<ScheduleInfo>>() {
                override fun onSuccess(t: List<ScheduleInfo>) {
                    scheduleList.clear()
                    scheduleList.addAll(t)
                    scheduleAdapter.notifyDataSetChanged()

                    information.value = null
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    information.value = "학사일정이 없습니다"
                    isLoading.value = true
                }
            })
    }

    fun calendarClick(year : Int, month : Int, day : Int) {
        val date = "%04d%02d%02d".format(year, month + 1, day)
        getSchedule(date)
        scheduleList.clear()
        scheduleAdapter.notifyDataSetChanged()
        isLoading.value = false
        information.value = null
    }
}