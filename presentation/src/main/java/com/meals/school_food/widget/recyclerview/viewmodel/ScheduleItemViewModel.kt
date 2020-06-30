package com.meals.school_food.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meals.domain.model.DetailSchedule

class ScheduleItemViewModel : ViewModel() {

    val date = MutableLiveData<String>()
    val name = MutableLiveData<String>()

    fun bind(schedule: DetailSchedule) {
        date.value = schedule.date
        name.value = schedule.name
    }
}