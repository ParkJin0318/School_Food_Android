package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
import com.meals.domain.dataSource.GetSchoolUseCase
import com.meals.domain.model.SchoolInformation
import com.meals.domain.model.School
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.recyclerview.adapter.SchoolAdapter
import io.reactivex.observers.DisposableSingleObserver

class SearchViewModel(
    private val getSearchUseCase: GetSchoolUseCase
): BaseViewModel() {

    val word = MutableLiveData<String>()
    val searchEvent = SingleLiveEvent<Unit>()

    val schoolAdapter = SchoolAdapter()
    val schoolList = ArrayList<SchoolInformation>()

    val completeEvent = SingleLiveEvent<Unit>()

    init {
        schoolAdapter.setList(schoolList)
    }

    fun getSchools() {
        addDisposable(getSearchUseCase.buildUseCaseObservable(GetSchoolUseCase.Params(word.value.toString())),
            object : DisposableSingleObserver<School>() {
                override fun onSuccess(t: School) {
                    addData(t)
                    completeEvent.call()
                }
                override fun onError(e: Throwable) { }
            })
    }

    fun addData(t: School) {
        schoolList.clear()
        for (item in t.schools) {
            schoolList.add(SchoolInformation(item.school_name, item.school_locate, item.office_code, item.school_id))
        }
        schoolAdapter.notifyDataSetChanged()
    }

    fun searchClick() {
        searchEvent.call()
    }
}
