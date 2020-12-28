package com.meals.school_food.viewmodel

import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetAllSchoolUseCase
import com.meals.domain.model.SchoolInfo
import com.meals.domain.usecase.InsertSchoolUseCase
import com.meals.school_food.R
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.adapter.RecyclerItem
import com.meals.school_food.widget.navigator.SchoolItemNavigator
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver

class SearchViewModel(
    private val getSearchUseCase: GetAllSchoolUseCase,
    private val insertSchoolUseCase: InsertSchoolUseCase
): BaseViewModel(), SchoolItemNavigator {

    val word = MutableLiveData<String>()
    val searchEvent = SingleLiveEvent<Unit>()

    val schoolItemList = MutableLiveData<ArrayList<RecyclerItem>>()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onErrorEvent = SingleLiveEvent<String>()

    fun getSchools() {
        addDisposable(getSearchUseCase.buildUseCaseObservable(GetAllSchoolUseCase.Params(word.value.toString())),
            object : DisposableSingleObserver<List<SchoolInfo>>() {
                override fun onSuccess(t: List<SchoolInfo>) {
                    schoolItemList.value = ArrayList(t.toRecyclerItemList())
                    isLoading.value = false
                }
                override fun onError(e: Throwable) {
                    onErrorEvent.value = e.message
                }
            })
    }

    private fun List<SchoolInfo>.toRecyclerItemList() = map { it.toViewModel() }

    private fun SchoolInfo.toViewModel() = SchoolItemViewModel(this).toRecyclerItem()

    private fun SchoolItemViewModel.toRecyclerItem() =
            RecyclerItem(
                    data = this,
                    navigator = this@SearchViewModel,
                    layoutId = R.layout.item_school
            )

    private fun insertSchoolInfo(schoolInfo: SchoolInfo) {
        addDisposable(insertSchoolUseCase.buildUseCaseObservable(InsertSchoolUseCase.Params(schoolInfo)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onSuccessEvent.call()
                }
                override fun onError(e: Throwable) {
                    onErrorEvent.value = e.message
                }
            })
    }

    fun searchClick() {
        searchEvent.call()
    }

    override fun onClickSchoolItem(schoolInfo: SchoolInfo) {
        insertSchoolInfo(schoolInfo)
    }
}
