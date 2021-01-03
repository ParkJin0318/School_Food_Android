package com.meals.school_food.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetAllSchoolUseCase
import com.meals.domain.model.SchoolInfo
import com.meals.domain.usecase.InsertSchoolUseCase
import com.meals.school_food.R
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.Event
import com.meals.school_food.widget.adapter.RecyclerItem
import com.meals.school_food.widget.navigator.SchoolItemNavigator
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver

class SearchViewModel(
    private val getSearchUseCase: GetAllSchoolUseCase,
    private val insertSchoolUseCase: InsertSchoolUseCase
): BaseViewModel(), SchoolItemNavigator {

    // View Binding LiveData
    val schoolNameText = MutableLiveData<String>()

    // ViewModel Logic LiveData
    private val _schoolItemList = MutableLiveData<ArrayList<RecyclerItem>>()
    val schoolItemList: LiveData<ArrayList<RecyclerItem>>
        get() = _schoolItemList

    private val _onSuccessEvent = MutableLiveData<Event<Boolean>>()
    val onSuccessEvent: LiveData<Event<Boolean>>
        get() = _onSuccessEvent

    private val _onErrorEvent = MutableLiveData<Event<Throwable>>()
    val onErrorEvent: LiveData<Event<Throwable>>
        get() = _onErrorEvent


    fun getSchools() {
        addDisposable(getSearchUseCase.buildUseCaseObservable(GetAllSchoolUseCase.Params(schoolNameText.value.toString())),
            object : DisposableSingleObserver<List<SchoolInfo>>() {
                override fun onSuccess(t: List<SchoolInfo>) {
                    _schoolItemList.value = ArrayList(t.toRecyclerItemList())
                    isLoading.value = false
                }
                override fun onError(e: Throwable) {
                    _onErrorEvent.value = Event(e)
                }
            })
    }

    private fun List<SchoolInfo>.toRecyclerItemList() = map { it.toViewModel() }

    private fun SchoolInfo.toViewModel() = SchoolItemViewModel(this).toRecyclerItem()

    private fun SchoolItemViewModel.toRecyclerItem() =
            RecyclerItem(
                    viewModel = this,
                    navigator = this@SearchViewModel,
                    layoutId = R.layout.item_school
            )

    private fun insertSchoolInfo(schoolInfo: SchoolInfo) {
        addDisposable(insertSchoolUseCase.buildUseCaseObservable(InsertSchoolUseCase.Params(schoolInfo)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    _onSuccessEvent.value = Event(true)
                }
                override fun onError(e: Throwable) {
                    _onErrorEvent.value = Event(e)
                }
            })
    }

    override fun onClickSchoolItem(schoolInfo: SchoolInfo) {
        insertSchoolInfo(schoolInfo)
    }
}
