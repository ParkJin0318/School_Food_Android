package com.meals.school_food.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meals.domain.model.SchoolInfo
import com.meals.domain.usecase.GetSchoolUseCase
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.Event
import io.reactivex.observers.DisposableSingleObserver

class SplashViewModel(
    private val getSchoolUseCase: GetSchoolUseCase
) : BaseViewModel() {

    // ViewModel Logic LiveData
    private val _onSuccessEvent = MutableLiveData<Event<Boolean>>()
    val onSuccessEvent: LiveData<Event<Boolean>>
        get() = _onSuccessEvent

    private val _onErrorEvent = MutableLiveData<Event<Boolean>>()
    val onErrorEvent: LiveData<Event<Boolean>>
        get() = _onErrorEvent


    init {
        getSchool()
    }

    private fun getSchool() {
        addDisposable(getSchoolUseCase.buildUseCaseObservable(), object : DisposableSingleObserver<SchoolInfo>() {
            override fun onSuccess(t: SchoolInfo) {
                _onSuccessEvent.value = Event(true)
            }
            override fun onError(e: Throwable) {
                _onErrorEvent.value = Event(true)
            }
        })
    }
}