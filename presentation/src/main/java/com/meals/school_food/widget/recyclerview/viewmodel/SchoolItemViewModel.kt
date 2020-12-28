package com.meals.school_food.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meals.domain.model.SchoolInfo

class SchoolItemViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val address = MutableLiveData<String>()

    fun bind(schoolInfo: SchoolInfo) {
        name.value = schoolInfo.school_name
        address.value = schoolInfo.school_locate
    }
}