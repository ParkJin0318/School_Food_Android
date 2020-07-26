package com.meals.school_food.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meals.domain.model.DetailSchedule
import java.text.SimpleDateFormat

class ScheduleItemViewModel : ViewModel() {

    val date = MutableLiveData<String>()
    val name = MutableLiveData<String>()

    fun bind(schedule: DetailSchedule) {
        date.value = getDateFormat(schedule.date)
        name.value = schedule.name
    }

    private fun getDateFormat(scheduleDate : String) : String {
        val strToDate = SimpleDateFormat("yyyyMMdd").parse(scheduleDate)
        return SimpleDateFormat("yyyy년 M월 d일").format(strToDate)
    }
}