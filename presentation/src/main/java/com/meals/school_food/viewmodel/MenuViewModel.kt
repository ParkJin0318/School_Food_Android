package com.meals.school_food.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meals.domain.model.SchoolInfo
import com.meals.domain.usecase.GetSchoolUseCase
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.Event
import io.reactivex.observers.DisposableSingleObserver

class MenuViewModel(
    private val getSchoolUseCase: GetSchoolUseCase
): BaseViewModel() {

    // View Binding LiveData
    val schoolNameText = MutableLiveData<String>()
    val schoolAddressText = MutableLiveData<String>()

    // ViewModel Logic LiveData
    private val _onSchoolChangeEvent = MutableLiveData<Event<Boolean>>()
    val onSchoolChangeEvent: LiveData<Event<Boolean>>
        get() = _onSchoolChangeEvent

    private val _onVersionEvent = MutableLiveData<Event<Boolean>>()
    val onVersionEvent: LiveData<Event<Boolean>>
        get() = _onVersionEvent


    init {
        getSchool()
    }

    private fun getSchool() {
        addDisposable(getSchoolUseCase.buildUseCaseObservable(), object : DisposableSingleObserver<SchoolInfo>() {
            override fun onSuccess(t: SchoolInfo) {
                schoolNameText.value = t.school_name
                schoolAddressText.value = t.school_locate
            }
            override fun onError(e: Throwable) { }
        })
    }

    fun onSchoolChangeClick() {
        _onSchoolChangeEvent.value = Event(true)
    }

    fun onVersionClick() {
        _onVersionEvent.value = Event(true)
    }
}