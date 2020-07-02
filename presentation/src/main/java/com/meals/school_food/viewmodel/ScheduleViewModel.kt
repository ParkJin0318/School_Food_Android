package com.meals.school_food.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.meals.data.util.Constants
import com.meals.data.util.SharedPreferenceManager
import com.meals.domain.dataSource.GetScheduleUseCase
import com.meals.domain.model.DetailSchedule
import com.meals.domain.model.Schedule
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.extension.getDate
import com.meals.school_food.widget.recyclerview.adapter.ScheduleAdapter
import io.reactivex.observers.DisposableSingleObserver
import java.text.SimpleDateFormat

class ScheduleViewModel(
    private val application: Application,
    private val getScheduleUseCase: GetScheduleUseCase
) : BaseViewModel() {

    private val schoolId = MutableLiveData<String>()
    val schoolName = MutableLiveData<String>()
    val information = MutableLiveData<String>()

    private val scheduleList = ArrayList<DetailSchedule>()
    val scheduleAdapter = ScheduleAdapter()

    init {
        getSchoolInformation()
        scheduleAdapter.setList(scheduleList)
        getSchedule(getDate("yyyyMMdd"))
    }

    private fun getSchoolInformation() {
        schoolId.value = SharedPreferenceManager.getSchoolId(application)
        schoolName.value = SharedPreferenceManager.getSchoolName(application)

        if(schoolId.value == null) {
            schoolName.value = "선택된 학교가 없습니다"
            information.value = "선택된 학교가 없습니다"
            isLoading.value = true
        }
    }

    private fun getSchedule(date : String) {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(schoolId.value.toString(), Constants.OFFICE_CODE, date)),
            object : DisposableSingleObserver<Schedule>() {
                override fun onSuccess(t: Schedule) {
                    successEvent(t)
                }
                override fun onError(e: Throwable) {
                    isLoading.value = true
                    information.value = "학사일정이 없습니다"
                }
            })
    }

    private fun successEvent(t: Schedule) {
        scheduleList.clear()
        for (item in t.schedules) {
            scheduleList.add(item)
        }
        scheduleAdapter.notifyDataSetChanged()
        isLoading.value = true
        information.value = null
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