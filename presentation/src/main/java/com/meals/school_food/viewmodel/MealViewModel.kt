package com.meals.school_food.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.meals.data.util.Constants
import com.meals.data.util.SharedPreferenceManager
import com.meals.domain.dataSource.GetMealUseCase
import com.meals.domain.model.Meal
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.recyclerview.MealAdapter
import io.reactivex.observers.DisposableSingleObserver
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MealViewModel(
    private val application: Application,
    private val getMealUseCase: GetMealUseCase
) : BaseViewModel() {

    val searchEvent = SingleLiveEvent<Unit>()
    val nullCheck = MutableLiveData<Boolean>(false)

    private val morningList = ArrayList<String>()
    private val lunchList = ArrayList<String>()
    private val dinnerList = ArrayList<String>()

    val morningAdapter = MealAdapter()
    val lunchAdapter = MealAdapter()
    val dinnerAdapter = MealAdapter()

    init {
        morningAdapter.setList(morningList)
        lunchAdapter.setList(lunchList)
        dinnerAdapter.setList(dinnerList)

        getId()
    }

    private fun getId() {
        val id = SharedPreferenceManager.getSchoolId(application)
        if (id != null) {
            getMeal(id)
        } else {
            nullCheck.value = true
            isLoading.value = true
        }
    }

    private fun getMeal(id : String) {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(id, Constants.OFFICE_CODE, today())),
            object : DisposableSingleObserver<Meal>() {
                override fun onSuccess(t: Meal) {
                    addData(t)
                    isLoading.value = true
                }
                override fun onError(e: Throwable) { }
            })
    }

    private fun addData(t: Meal) {
        morningList.addAll(t.meals[0].split("<br/>"))
        lunchList.addAll(t.meals[1].split("<br/>"))
        dinnerList.addAll(t.meals[2].split("<br/>"))

        morningAdapter.notifyDataSetChanged()
        lunchAdapter.notifyDataSetChanged()
        dinnerAdapter.notifyDataSetChanged()
    }

    private fun today() : String {
        val format = SimpleDateFormat("yyyyMMdd")
        return format.format(Date(System.currentTimeMillis()))
    }

    fun searchClick() {
        searchEvent.call()
    }

}