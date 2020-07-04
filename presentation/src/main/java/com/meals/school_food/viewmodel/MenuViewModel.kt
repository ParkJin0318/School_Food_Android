package com.meals.school_food.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.meals.data.util.SharedPreferenceManager
import com.meals.school_food.base.BaseViewModel

class MenuViewModel(
    private val application: Application
) : BaseViewModel() {

    val schoolName = MutableLiveData<String>()
    val schoolAddress = MutableLiveData<String>()

    init {
        getSchoolInformation()
    }

    private fun getSchoolInformation() {
        SharedPreferenceManager.getSchoolName(application).let {
            if (it != null) schoolName.value = it
            else schoolName.value = "선택된 학교가 없습니다"
        }
        SharedPreferenceManager.getSchoolAddress(application).let {
            if (it != null) schoolAddress.value = it
            else schoolAddress.value = "선택된 학교가 없습니다"
        }
    }
}