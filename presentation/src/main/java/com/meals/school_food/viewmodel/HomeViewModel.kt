package com.meals.school_food.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meals.domain.usecase.GetMealUseCase
import com.meals.domain.usecase.GetScheduleUseCase
import com.meals.domain.model.MealInfo
import com.meals.domain.model.ScheduleInfo
import com.meals.domain.model.TimeInfo
import com.meals.school_food.R
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.Event
import com.meals.school_food.widget.adapter.RecyclerItem
import com.meals.school_food.widget.extension.*
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(
    private val getMealUseCase: GetMealUseCase,
    private val getScheduleUseCase: GetScheduleUseCase
) : BaseViewModel() {

    // View Binding LiveData
    val mealText = MutableLiveData<String>()
    val timeText = MutableLiveData<String>()

    // ViewModel Logic LiveData
    private val _scheduleItemList = MutableLiveData<ArrayList<RecyclerItem>>()
    val scheduleItemList: LiveData<ArrayList<RecyclerItem>>
        get() = _scheduleItemList

    private val _timeInfo = MutableLiveData<TimeInfo>()
    val timeInfo: LiveData<TimeInfo>
        get() = _timeInfo

    private val _onScheduleDetailEvent = MutableLiveData<Event<Boolean>>()
    val onScheduleDetailEvent: LiveData<Event<Boolean>>
        get() = _onScheduleDetailEvent

    private val _onMealDetailEvent = MutableLiveData<Event<Boolean>>()
    val onMealDetailEvent: LiveData<Event<Boolean>>
        get() = _onMealDetailEvent

    private val _onErrorEvent = MutableLiveData<Event<Throwable>>()
    val onErrorEvent: LiveData<Event<Throwable>>
        get() = _onErrorEvent


    init {
        getMeal()
        getSchedule()
    }

    private fun getMeal() {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(Date().dayDateFormat())),
            object : DisposableSingleObserver<MealInfo>() {
                override fun onSuccess(t: MealInfo) {
                    setCurrentMeal(t)
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    _onErrorEvent.value = Event(e)
                    isLoading.value = true
                }
            })
    }

    private fun setCurrentMeal(mealInfo: MealInfo) {
        val now = Date().getFormatDate()
        when {
            now.before("08:20".getTime()) -> {
                mealText.value = mealInfo.breakfast
                _timeInfo.value = TimeInfo.BREAKFAST
            }
            now.before("13:20".getTime()) -> {
                mealText.value = mealInfo.lunch
                _timeInfo.value = TimeInfo.LUNCH
            }
            else -> {
                mealText.value = mealInfo.dinner
                _timeInfo.value = TimeInfo.DINNER
            }
        }
    }

    private fun getSchedule() {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(Date().monthDateFormat())),
            object : DisposableSingleObserver<List<ScheduleInfo>>() {
                override fun onSuccess(t: List<ScheduleInfo>) {
                    _scheduleItemList.value = ArrayList(t.toRecyclerItemList())
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    isLoading.value = true
                }
            })
    }

    private fun List<ScheduleInfo>.toRecyclerItemList() = map { it.toViewModel() }

    private fun ScheduleInfo.toViewModel() = ScheduleItemViewModel(this).toRecyclerItem()

    private fun ScheduleItemViewModel.toRecyclerItem() =
            RecyclerItem(
                    viewModel = this,
                    navigator = this@HomeViewModel,
                    layoutId = R.layout.item_schedule
            )

    fun onScheduleDetailClick() {
        _onScheduleDetailEvent.value = Event(true)
    }

    fun onMealDetailClick() {
        _onMealDetailEvent.value = Event(true)
    }
}