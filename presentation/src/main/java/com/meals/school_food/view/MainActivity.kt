package com.meals.school_food.view

import com.meals.school_food.R
import com.meals.school_food.base.BaseActivity
import com.meals.school_food.databinding.ActivityMainBinding
import com.meals.school_food.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel
        get() = getViewModel(MainViewModel::class)

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun init() { }

    override fun observerViewModel() {
        with(viewModel) {

        }
    }
}