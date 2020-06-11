package com.meals.school_food.viewmodel

import android.app.Application
import com.meals.data.util.Constants
import com.meals.data.util.SharedPreferenceManager
import com.meals.domain.dataSource.GetMealUseCase
import com.meals.domain.model.Meal
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.recyclerview.MealAdapter
import io.reactivex.observers.DisposableSingleObserver
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MealViewModel(
    private val getMealUseCase: GetMealUseCase,
    private val application: Application
) : BaseViewModel() {

    private val format = SimpleDateFormat("yyyyMMdd")
    private val date = format.format(Date(System.currentTimeMillis()))

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
        getMeal()
    }

    private fun getMeal() {
        val id = SharedPreferenceManager.getSchoolId(application)
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(id.toString(), Constants.OFFICE_CODE, date)),
            object : DisposableSingleObserver<Meal>() {
                override fun onSuccess(t: Meal) {
                    addData(t)
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

        isLoading.value = true
    }

}