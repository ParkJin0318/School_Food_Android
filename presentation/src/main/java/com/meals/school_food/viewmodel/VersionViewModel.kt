package com.meals.school_food.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent

class VersionViewModel(
    private val application: Application
) : BaseViewModel() {

    val version = MutableLiveData<String>()
    val backEvent = SingleLiveEvent<Unit>()

    init {
        version.value = getAppVersion()
    }

    private fun getAppVersion(): String {
        val packageInfo = application.packageManager.getPackageInfo(application.packageName, 0)
        return packageInfo.versionName
    }

    fun backClick() {
        backEvent.call()
    }
}