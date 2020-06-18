package com.meals.school_food.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meals.domain.model.DetailSearch

class SchoolItemViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val address = MutableLiveData<String>()

    fun bind(detailSearch: DetailSearch) {
        name.value = detailSearch.school_name
        address.value = detailSearch.school_locate
    }
}