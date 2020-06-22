package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
import com.meals.domain.dataSource.GetSearchUseCase
import com.meals.domain.model.DetailSearch
import com.meals.domain.model.Search
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.recyclerview.adapter.SchoolAdapter
import io.reactivex.observers.DisposableSingleObserver

class SearchViewModel(
    private val getSearchUseCase: GetSearchUseCase
): BaseViewModel() {

    val word = MutableLiveData<String>()
    val searchEvent = SingleLiveEvent<Unit>()

    val schoolAdapter = SchoolAdapter()
    val schoolList = ArrayList<DetailSearch>()

    val completeEvent = SingleLiveEvent<Unit>()

    init {
        schoolAdapter.setList(schoolList)
    }

    fun getSchools() {
        addDisposable(getSearchUseCase.buildUseCaseObservable(GetSearchUseCase.Params(word.value.toString())),
            object : DisposableSingleObserver<Search>() {
                override fun onSuccess(t: Search) {
                    addData(t)
                    completeEvent.call()
                }
                override fun onError(e: Throwable) { }
            })
    }

    fun addData(t: Search) {
        schoolList.clear()
        for (item in t.schools) {
            schoolList.add(DetailSearch(item.school_name, item.school_locate, item.office_code, item.school_id))
        }
        schoolAdapter.notifyDataSetChanged()
    }

    fun searchClick() {
        searchEvent.call()
    }
}
