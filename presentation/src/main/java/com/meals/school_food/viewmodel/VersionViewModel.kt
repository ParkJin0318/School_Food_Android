package com.meals.school_food.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.Event

class VersionViewModel(
    private val application: Application
) : BaseViewModel() {

    // View Binding LiveData
    val versionText = MutableLiveData<String>()

    // ViewModel Logic LiveData
    private val _onBackEvent = MutableLiveData<Event<Boolean>>()
    val onBackEvent: LiveData<Event<Boolean>>
        get() = _onBackEvent


    init {
        versionText.value = getAppVersion()
    }

    private fun getAppVersion(): String {
        val packageInfo = application.packageManager.getPackageInfo(application.packageName, 0)
        return packageInfo.versionName
    }

    fun backClick() {
        _onBackEvent.value = Event(true)
    }
}