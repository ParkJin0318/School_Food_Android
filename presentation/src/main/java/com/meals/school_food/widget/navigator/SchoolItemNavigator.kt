package com.meals.school_food.widget.navigator

import com.meals.domain.model.SchoolInfo

interface SchoolItemNavigator {
    fun onClickSchoolItem(schoolInfo: SchoolInfo)
}