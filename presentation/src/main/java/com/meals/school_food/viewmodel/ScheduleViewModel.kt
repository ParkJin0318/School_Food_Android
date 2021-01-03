package com.meals.school_food.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetScheduleUseCase
import com.meals.domain.model.ScheduleInfo
import com.meals.school_food.R
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.adapter.RecyclerItem
import com.meals.school_food.widget.extension.dayDateFormat
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import kotlin.collections.ArrayList

class ScheduleViewModel(
    private val getScheduleUseCase: GetScheduleUseCase
) : BaseViewModel() {

    // View Binding LiveData
    val guideText = MutableLiveData<String>()

    // ViewModel Logic LiveData
    private val _scheduleItemList = MutableLiveData<ArrayList<RecyclerItem>>()
    val scheduleItemList: LiveData<ArrayList<RecyclerItem>>
        get() = _scheduleItemList


    init {
        getSchedule(Date().dayDateFormat())
    }

    private fun getSchedule(date : String) {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(date)),
            object : DisposableSingleObserver<List<ScheduleInfo>>() {
                override fun onSuccess(t: List<ScheduleInfo>) {
                    _scheduleItemList.value = ArrayList(t.toRecyclerItemList())

                    guideText.value = null
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    guideText.value = "학사일정이 없습니다"
                    isLoading.value = true
                }
            })
    }

    private fun List<ScheduleInfo>.toRecyclerItemList() = map { it.toViewModel() }

    private fun ScheduleInfo.toViewModel() = ScheduleItemViewModel(this).toRecyclerItem()

    private fun ScheduleItemViewModel.toRecyclerItem() =
            RecyclerItem(
                    viewModel = this,
                    navigator = this@ScheduleViewModel,
                    layoutId = R.layout.item_schedule
            )

    fun calendarClick(year : Int, month : Int, day : Int) {
        val date = "%04d%02d%02d".format(year, month + 1, day)
        isLoading.value = false
        guideText.value = null
        getSchedule(date)
    }
}