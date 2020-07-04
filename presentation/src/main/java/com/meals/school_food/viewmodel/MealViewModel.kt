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
import java.text.SimpleDateFormat

class MealViewModel(
    private val application: Application,
    private val getMealUseCase: GetMealUseCase
) : BaseViewModel() {

    val schoolId = MutableLiveData<String>()
    val schoolName = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val mealCheck = MutableLiveData<String>()

    val dateEvent = SingleLiveEvent<Unit>()

    val morningAdapter = MealAdapter()
    val lunchAdapter = MealAdapter()
    val dinnerAdapter = MealAdapter()

    private val morningList = ArrayList<String>()
    private val lunchList = ArrayList<String>()
    private val dinnerList = ArrayList<String>()

    init {
        morningAdapter.setList(morningList)
        lunchAdapter.setList(lunchList)
        dinnerAdapter.setList(dinnerList)

        getSchoolInformation()
    }

    private fun getSchoolInformation() {
        date.value = getDate("yyyy년 MM월 dd일")
        schoolName.value = SharedPreferenceManager.getSchoolName(application)
        SharedPreferenceManager.getSchoolId(application).let {
            if (it == null) {
                schoolName.value = "선택된 학교가 없습니다"
                mealCheck.value = "선택된 학교가 없습니다"
                isLoading.value = true
            } else {
                schoolId.value = it
                getMeal(getDateFormat(date.value.toString()))
            }
        }
    }

    private fun getMeal(date : String) {
        addDisposable(getMealUseCase.buildUseCaseObservable(GetMealUseCase.Params(schoolId.value.toString(), Constants.OFFICE_CODE, date)),
            object : DisposableSingleObserver<Meal>() {
                override fun onSuccess(t: Meal) {
                    addMealData(t)
                    isLoading.value = true
                }
                override fun onError(e: Throwable) {
                    mealCheck.value = "급식이 없습니다"
                    isLoading.value = true
                }
            })
    }

    private fun addMealData(t: Meal) {
        clearMeal()

        for (i in 0..2) {
            t.meals[i].let { it->
                it?.split("<br/>").let {
                    if (it != null) {
                        when (i) {
                            0 -> morningList.addAll(it)
                            1 -> lunchList.addAll(it)
                            2 -> dinnerList.addAll(it)
                        }
                    }
                }
            }
        }
        changeMeal()
    }

    fun setDate(year : Int, month : Int, day : Int) {
        date.value = "${year}년 ${month}월 ${day}일"
        getMeal(getDateFormat(date.value.toString()))
        clearMeal()
        changeMeal()
        isLoading.value = false
        mealCheck.value = null
    }

    private fun getDateFormat(scheduleDate : String) : String {
        val strToDate = SimpleDateFormat("yyyy년 MM월 dd일").parse(scheduleDate)
        return SimpleDateFormat("yyyyMMdd").format(strToDate)
    }

    private fun clearMeal() {
        morningList.clear()
        lunchList.clear()
        dinnerList.clear()
    }

    private fun changeMeal() {
        morningAdapter.notifyDataSetChanged()
        lunchAdapter.notifyDataSetChanged()
        dinnerAdapter.notifyDataSetChanged()
    }

    fun dateClick() {
        dateEvent.call()
    }
}