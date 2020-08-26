package com.meals.school_food.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.meals.data.util.SharedPreferenceManager
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
    private val schoolList = ArrayList<SchoolInformation>()

    init {
        schoolAdapter.setList(schoolList)
    }

    fun getSchools() {
        addDisposable(getSearchUseCase.buildUseCaseObservable(GetSchoolUseCase.Params(word.value.toString())),
            object : DisposableSingleObserver<School>() {
                override fun onSuccess(t: School) {
                    addData(t)
                    isLoading.value = false
                }
                override fun onError(e: Throwable) { }
            })
    }

    fun addData(t: School) {
        schoolList.clear()
        t.schools.forEach {
            schoolList.add(SchoolInformation(it.school_name, it.school_locate, it.office_code, it.school_id))
        }
        schoolAdapter.notifyDataSetChanged()
    }

    fun setSchoolInformation(application : Application) {
        SharedPreferenceManager.setSchoolInformation(
            application,
            with(schoolList[schoolAdapter.click.value!!]) {
                SchoolInformation(school_name, school_locate, office_code, school_id)
            }
        )
    }

    fun searchClick() {
        searchEvent.call()
    }
}
