package com.meals.school_food.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
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

    val schoolName = MutableLiveData<String>()
    private val officeCode = MutableLiveData<String>()

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
    private val scheduleList = ArrayList<DetailSchedule>()

    init {
        morningAdapter.setList(morningList)
        lunchAdapter.setList(lunchList)
        dinnerAdapter.setList(dinnerList)
        scheduleAdapter.setList(scheduleList)

        date.value = getDate("yyyy년 M월 d일")
        getSchoolInformation()
        isMorning.value = true
    }

    private fun getSchoolInformation() {
        SharedPreferenceManager.getSchoolName(application).let {
            if (it != null) schoolName.value = it
            else schoolName.value = "선택된 학교가 없습니다"
        }
        SharedPreferenceManager.getOfficeCode(application).let {
            if (it != null) officeCode.value = it
            else schoolName.value = "선택된 학교가 없습니다"
        }
        SharedPreferenceManager.getSchoolId(application).let {
            if (it != null) foundSchoolId(it)
            else notFoundSchoolId("선택된 학교가 없습니다")
        }
    }

    private fun foundSchoolId(id : String) {
        getMeal(id)
        getSchedule(id)
    }

    private fun notFoundSchoolId(text : String) {
        mealCheck.value = text
        scheduleCheck.value = text
        isLoading.value = true
    }

    private fun getMeal(id : String) {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(id, officeCode.value!!, getDate("yyyyMMdd"))),
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
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(id, officeCode.value!!, getDate("yyyyMM"))),
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
            t.meals[i]?.let {
                when(i) {
                    0 -> morningList.addAll(it.split("<br/>"))
                    1 -> lunchList.addAll(it.split("<br/>"))
                    2 -> dinnerList.addAll(it.split("<br/>"))
                    else -> return@let
                }
            }
        }
        morningAdapter.notifyDataSetChanged()
        lunchAdapter.notifyDataSetChanged()
        dinnerAdapter.notifyDataSetChanged()
    }

    private fun addScheduleData(t: Schedule) {
        scheduleList.clear()
        t.schedules.forEach {
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