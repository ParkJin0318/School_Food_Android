package com.meals.school_food.viewmodel

import com.meals.domain.model.SchoolInfo
import com.meals.domain.usecase.GetSchoolUseCase
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import io.reactivex.observers.DisposableSingleObserver

class SplashViewModel(
    private val getSchoolUseCase: GetSchoolUseCase
) : BaseViewModel() {

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onErrorEvent = SingleLiveEvent<Unit>()

    init {
        getSchool()
    }

    private fun getSchool() {
        addDisposable(getSchoolUseCase.buildUseCaseObservable(), object : DisposableSingleObserver<SchoolInfo>() {
            override fun onSuccess(t: SchoolInfo) {
                onSuccessEvent.call()
            }
            override fun onError(e: Throwable) {
                onErrorEvent.call()
            }
        })
    }
}