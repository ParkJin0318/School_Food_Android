package com.meals.school_food.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.meals.data.util.Constants
import com.meals.data.util.SharedPreferenceManager
import com.meals.domain.dataSource.GetMealUseCase
import com.meals.domain.dataSource.GetScheduleUseCase
import com.meals.domain.model.DetailSchedule
import com.meals.domain.model.Meal
import com.meals.domain.model.Schedule
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.recyclerview.adapter.MealAdapter
import com.meals.school_food.widget.recyclerview.adapter.ScheduleAdapter
import io.reactivex.observers.DisposableSingleObserver
import kotlin.collections.ArrayList

class HomeViewModel(
    private val application: Application,
    private val getMealUseCase: GetMealUseCase,
    private val getScheduleUseCase: GetScheduleUseCase
) : BaseViewModel() {

    val time = MutableLiveData<String>("아침")
    val date = MutableLiveData<String>()
    val schoolName = MutableLiveData<String>()

    val mealCheck = MutableLiveData<String>()
    val scheduleCheck = MutableLiveData<String>()

    val searchEvent = SingleLiveEvent<Unit>()
    val morningEvent = SingleLiveEvent<Unit>()
    val lunchEvent = SingleLiveEvent<Unit>()
    val dinerEvent = SingleLiveEvent<Unit>()

    val morningAdapter = MealAdapter()
    val lunchAdapter = MealAdapter()
    val dinnerAdapter = MealAdapter()
    val scheduleAdapter = ScheduleAdapter()

    private val morningList = ArrayList<String>()
    private val lunchList = ArrayList<String>()
    private val dinnerList = ArrayList<String>()
    private val scheduleList = ArrayList<DetailSchedule>()

    init {
        morningAdapter.setList(morningList)
        lunchAdapter.setList(lunchList)
        dinnerAdapter.setList(dinnerList)
        scheduleAdapter.setList(scheduleList)

        getSchoolInformation()
    }

    private fun getSchoolInformation() {
        date.value = getDate("yyyy년 M월 d일")
        schoolName.value = SharedPreferenceManager.getSchoolName(application)

        SharedPreferenceManager.getSchoolId(application).let {
            if (it != null) {
                getMeal(it)
                getSchedule(it)
            } else {
                schoolName.value = "선택된 학교가 없습니다"
                mealCheck.value = "선택된 학교가 없습니다"
                scheduleCheck.value = "선택된 학교가 없습니다"
                isLoading.value = true
            }
        }
    }

    private fun getMeal(id : String) {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(id, Constants.OFFICE_CODE, getDate("yyyyMMdd"))),
            object : DisposableSingleObserver<Meal>() {
                override fun onSuccess(t: Meal) {
                    addMealData(t)
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    mealCheck.value = "급식이 없습니다"
                    isLoading.value = true
                }
            })
    }

    private fun getSchedule(id : String) {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(id, Constants.OFFICE_CODE, getDate("yyyyMM"))),
            object : DisposableSingleObserver<Schedule>() {
                override fun onSuccess(t: Schedule) {
                    addScheduleData(t)
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    scheduleCheck.value = "학사일정이 없습니다"
                    isLoading.value = true
                }
            })
    }

    private fun addMealData(t: Meal) {
        morningList.clear()
        lunchList.clear()
        dinnerList.clear()

        for (i in 0..2) {
            t.meals[i].let {
                if (it != null) {
                    when(i) {
                        0 -> morningList.addAll(it.split("<br/>"))
                        1 -> lunchList.addAll(it.split("<br/>"))
                        2 -> dinnerList.addAll(it.split("<br/>"))
                    }
                }
            }
        }
        morningAdapter.notifyDataSetChanged()
        lunchAdapter.notifyDataSetChanged()
        dinnerAdapter.notifyDataSetChanged()
    }

    private fun addScheduleData(t: Schedule) {
        scheduleList.clear()
        for (item in t.schedules) {
            scheduleList.add(item)
        }
        scheduleAdapter.notifyDataSetChanged()
    }

    fun searchClick() {
        searchEvent.call()
    }

    fun morningClick() {
        morningEvent.call()
    }

    fun lunchClick() {
        lunchEvent.call()
    }

    fun dinerClick() {
        dinerEvent.call()
    }
}