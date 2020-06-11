package com.meals.school_food.widget.recyclerview

import androidx.lifecycle.MutableLiveData
import com.meals.school_food.base.BaseViewModel

class MealItemViewModel : BaseViewModel() {

    val meal = MutableLiveData<String>()

     fun bind(meal : String) {
         this.meal.value = meal
     }
}