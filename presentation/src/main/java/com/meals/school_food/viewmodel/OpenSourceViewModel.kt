package com.meals.school_food.viewmodel

import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent

class OpenSourceViewModel : BaseViewModel() {

    val backEvent = SingleLiveEvent<Unit>()

    fun backClick() {
        backEvent.call()
    }

}