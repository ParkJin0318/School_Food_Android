package com.meals.school_food.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.meals.data.util.Constants
import com.meals.data.util.SharedPreferenceManager
import com.meals.domain.dataSource.GetScheduleUseCase
import com.meals.domain.model.DetailSchedule
import com.meals.domain.model.Schedule
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.recyclerview.adapter.ScheduleAdapter
import io.reactivex.observers.DisposableSingleObserver
import java.text.SimpleDateFormat

class ScheduleViewModel(
    private val application: Application,
    private val getScheduleUseCase: GetScheduleUseCase
) : BaseViewModel() {

    private val schoolId = MutableLiveData<String>()
    private val officeCode = MutableLiveData<String>()
    val schoolName = MutableLiveData<String>()
    val information = MutableLiveData<String>()

    private val scheduleList = ArrayList<DetailSchedule>()
    val scheduleAdapter = ScheduleAdapter()

    init {
        scheduleAdapter.setList(scheduleList)

        getSchoolInformation()
        getSchedule(getDate("yyyyMMdd"))
    }

    private fun getSchoolInformation() {
        SharedPreferenceManager.getSchoolId(application).let {
            if (it != null) schoolId.value = it
            else notFoundSchoolId("선택된 학교가 없습니다")
        }
        SharedPreferenceManager.getOfficeCode(application).let {
            if (it != null) officeCode.value = it
            else schoolName.value = "선택된 학교가 없습니다"
        }
        SharedPreferenceManager.getSchoolName(application).let {
            if (it != null) schoolName.value = it
            else schoolName.value = "선택된 학교가 없습니다"
        }
    }

    private fun notFoundSchoolId(text : String) {
        information.value = text
        isLoading.value = true
    }

    private fun getSchedule(date : String) {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(schoolId.value!!, officeCode.value!!, date)),
            object : DisposableSingleObserver<Schedule>() {
                override fun onSuccess(t: Schedule) {
                    addScheduleData(t)
                    information.value = null
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    information.value = "학사일정이 없습니다"
                    isLoading.value = true
                }
            })
    }

    private fun addScheduleData(t: Schedule) {
        scheduleList.clear()
        for (item in t.schedules) {
            scheduleList.add(item)
        }
        scheduleAdapter.notifyDataSetChanged()
    }

    fun calendarClick(year : Int, month : Int, day : Int) {
        getSchedule(getDateToString(year, month, day))
        scheduleList.clear()
        scheduleAdapter.notifyDataSetChanged()
        isLoading.value = false
        information.value = null
    }

    private fun getDateToString(year : Int, month : Int, day : Int) : String {
        val date = "${year}${month+1}${day}"
        val strToDate = SimpleDateFormat("yyyyMd").parse(date)
        return SimpleDateFormat("yyyyMMdd").format(strToDate)
    }
}