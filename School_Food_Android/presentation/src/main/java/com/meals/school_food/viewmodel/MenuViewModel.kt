package com.meals.school_food.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent

class MenuViewModel(
    private val application: Application
) : BaseViewModel() {

    val schoolName = MutableLiveData<String>()
    val schoolAddress = MutableLiveData<String>()

    val schoolChangeEvent = SingleLiveEvent<Unit>()
    val openSourceEvent = SingleLiveEvent<Unit>()
    val versionEvent = SingleLiveEvent<Unit>()

    init {

    }

    fun changeClick() {
        schoolChangeEvent.call()
    }

    fun openSourceClick() {
        openSourceEvent.call()
    }

    fun versionClick() {
        versionEvent.call()
    }
}