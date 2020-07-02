package com.meals.school_food.view.fragment

import com.meals.school_food.R
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.FragmentMealBinding
import com.meals.school_food.viewmodel.MealViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MealFragment : BaseFragment<FragmentMealBinding, MealViewModel>() {

    override val viewModel: MealViewModel
        get() = getViewModel(MealViewModel::class)

    override val layoutRes: Int
        get() = R.layout.fragment_meal

    override fun observerViewModel() { }
}