package com.meals.school_food.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.meals.school_food.R
import com.meals.school_food.databinding.ItemMealBinding

class MealAdapter : RecyclerView.Adapter<MealAdapter.ViewHolder>(){

    private lateinit var mealList : ArrayList<String>

    fun setList(list : ArrayList<String>) {
        if(::mealList.isInitialized) return
        mealList = list
    }

    class ViewHolder(private val binding : ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MealItemViewModel()

        fun bind(meal: String) {
            viewModel.bind(meal)
            binding.viewModel = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_meal, parent, false))
    }

    override fun getItemCount(): Int {
        return if(::mealList.isInitialized) mealList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        with(holder) {
            bind(mealList[position])
        }
    }
}