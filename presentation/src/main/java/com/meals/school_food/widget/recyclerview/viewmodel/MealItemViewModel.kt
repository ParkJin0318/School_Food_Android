package com.meals.school_food.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MealItemViewModel : ViewModel() {

    val meal = MutableLiveData<String>()

     fun bind(meal : String) {
         this.meal.value = meal
     }
}