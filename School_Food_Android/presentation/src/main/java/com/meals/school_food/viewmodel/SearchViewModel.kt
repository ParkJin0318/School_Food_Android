package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetAllSchoolUseCase
import com.meals.domain.model.SchoolInfo
import com.meals.domain.usecase.InsertSchoolUseCase
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.recyclerview.adapter.SchoolAdapter
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver

class SearchViewModel(
    private val getSearchUseCase: GetAllSchoolUseCase,
    private val insertSchoolUseCase: InsertSchoolUseCase
): BaseViewModel() {

    val word = MutableLiveData<String>()
    val searchEvent = SingleLiveEvent<Unit>()

    val schoolAdapter = SchoolAdapter()
    private val schoolList = ArrayList<SchoolInfo>()

    init {
        schoolAdapter.setList(schoolList)
    }

    fun getSchools() {
        addDisposable(getSearchUseCase.buildUseCaseObservable(GetAllSchoolUseCase.Params(word.value.toString())),
            object : DisposableSingleObserver<List<SchoolInfo>>() {
                override fun onSuccess(t: List<SchoolInfo>) {
                    addData(t)
                    isLoading.value = false
                }
                override fun onError(e: Throwable) { }
            })
    }

    fun addData(t: List<SchoolInfo>) {
        schoolList.clear()
        t.forEach {
            schoolList.add(SchoolInfo(it.school_name, it.school_locate, it.office_code, it.school_id))
        }
        schoolAdapter.notifyDataSetChanged()
    }

    fun setSchoolInformation() {
        with(schoolList[schoolAdapter.click.value!!]) {
            val schoolInfo = SchoolInfo(school_name, school_locate, office_code, school_id)
            addDisposable(insertSchoolUseCase.buildUseCaseObservable(InsertSchoolUseCase.Params(schoolInfo)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {

                }
                override fun onError(e: Throwable) {

                }
            })
        }

    }

    fun searchClick() {
        searchEvent.call()
    }
}
