package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
import com.meals.domain.model.SchoolInfo
import com.meals.domain.usecase.GetSchoolUseCase
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import io.reactivex.observers.DisposableSingleObserver

class MenuViewModel(
    private val getSchoolUseCase: GetSchoolUseCase
): BaseViewModel() {

    val schoolName = MutableLiveData<String>()
    val schoolAddress = MutableLiveData<String>()

    val schoolChangeEvent = SingleLiveEvent<Unit>()
    val versionEvent = SingleLiveEvent<Unit>()

    init {
        getSchool()
    }

    private fun getSchool() {
        addDisposable(getSchoolUseCase.buildUseCaseObservable(), object : DisposableSingleObserver<SchoolInfo>() {
            override fun onSuccess(t: SchoolInfo) {
                schoolName.value = t.school_name
                schoolAddress.value = t.school_locate
            }
            override fun onError(e: Throwable) { }
        })
    }

    fun changeClick() {
        schoolChangeEvent.call()
    }

    fun versionClick() {
        versionEvent.call()
    }
}