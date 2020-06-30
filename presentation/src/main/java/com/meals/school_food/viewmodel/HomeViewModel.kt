package com.meals.school_food.viewmodel

import android.app.Application
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
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(
    private val application: Application,
    private val getMealUseCase: GetMealUseCase,
    private val getScheduleUseCase: GetScheduleUseCase
) : BaseViewModel() {

    val check = MutableLiveData<String>("아침")
    val date = MutableLiveData<String>()
    val schoolName = MutableLiveData<String>()
    val information = MutableLiveData<String>()

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
        val schoolId = SharedPreferenceManager.getSchoolId(application)
        schoolName.value = SharedPreferenceManager.getSchoolName(application)
        date.value = todayDate("yyyy/MM/dd")

        if(schoolId == null) {
            schoolName.value = "선택된 학교가 없습니다"
            information.value = "선택된 학교가 없습니다"
            isLoading.value = true
        } else {
            getMeal(schoolId)
            getSchedule(schoolId)
        }
    }

    private fun getMeal(id : String) {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(id, Constants.OFFICE_CODE, todayDate("yyyyMMdd"))),
            object : DisposableSingleObserver<Meal>() {
                override fun onSuccess(t: Meal) {
                    addMealData(t)
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    information.value = "급식이 없습니다"
                    isLoading.value = true
                }
            })
    }

    private fun addMealData(t: Meal) {
        morningList.clear()
        lunchList.clear()
        dinnerList.clear()

        try {
            morningList.addAll(t.meals[0].split("<br/>"))
            lunchList.addAll(t.meals[1].split("<br/>"))
            dinnerList.addAll(t.meals[2].split("<br/>"))
        } catch (e : Exception) {
            e.printStackTrace()
        }
        morningAdapter.notifyDataSetChanged()
        lunchAdapter.notifyDataSetChanged()
        dinnerAdapter.notifyDataSetChanged()
    }

    private fun getSchedule(id : String) {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(id, Constants.OFFICE_CODE, todayDate("yyyyMM"))),
            object : DisposableSingleObserver<Schedule>() {
                override fun onSuccess(t: Schedule) {
                    addScheduleData(t)
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {

                }
            })
    }

    private fun addScheduleData(t: Schedule) {
        for (item in t.schedules) {
            scheduleList.add(item)
        }
        scheduleAdapter.notifyDataSetChanged()
    }

    private fun todayDate(pattern : String) : String {
        val format = SimpleDateFormat(pattern)
        return format.format(Date(System.currentTimeMillis()))
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