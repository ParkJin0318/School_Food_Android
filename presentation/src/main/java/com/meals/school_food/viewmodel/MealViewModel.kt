package com.meals.school_food.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.meals.data.util.Constants
import com.meals.data.util.SharedPreferenceManager
import com.meals.domain.dataSource.GetMealUseCase
import com.meals.domain.model.Meal
import com.meals.school_food.base.BaseViewModel
import com.meals.school_food.widget.SingleLiveEvent
import com.meals.school_food.widget.recyclerview.adapter.MealAdapter
import io.reactivex.observers.DisposableSingleObserver
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MealViewModel(
    private val application: Application,
    private val getMealUseCase: GetMealUseCase
) : BaseViewModel() {

    val searchEvent = SingleLiveEvent<Unit>()

    val information = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val schoolName = MutableLiveData<String>()

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

        getSchoolInformation()
    }

    private fun getSchoolInformation() {
        val schoolId = SharedPreferenceManager.getSchoolId(application)
        schoolName.value = SharedPreferenceManager.getSchoolName(application)
        date.value = todayDate("yyyy/MM/dd")

        if(schoolId == null) {
            information.value = "선택된 학교가 없습니다."
            isLoading.value = true
        } else {
            getMeal(schoolId)
        }
    }

    private fun getMeal(id : String) {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(id, Constants.OFFICE_CODE, todayDate("yyyyMMdd"))),
            object : DisposableSingleObserver<Meal>() {
                override fun onSuccess(t: Meal) {
                    addData(t)
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    information.value = "급식이 없습니다."
                    isLoading.value = true
                }
            })
    }

    private fun addData(t: Meal) {
        morningList.clear()
        lunchList.clear()
        dinnerList.clear()

        try {
            morningList.addAll(t.meals[0].split("<br/>"))
            lunchList.addAll(t.meals[1].split("<br/>"))
            dinnerList.addAll(t.meals[2].split("<br/>"))
        } catch (e : Exception) {
            e.printStackTrace()
        }
        notifyDataSetChanged()
    }

    private fun notifyDataSetChanged() {
        morningAdapter.notifyDataSetChanged()
        lunchAdapter.notifyDataSetChanged()
        dinnerAdapter.notifyDataSetChanged()
    }

    private fun todayDate(pattern : String) : String {
        val format = SimpleDateFormat(pattern)
        return format.format(Date(System.currentTimeMillis()))
    }

    fun searchClick() {
        searchEvent.call()
    }
}