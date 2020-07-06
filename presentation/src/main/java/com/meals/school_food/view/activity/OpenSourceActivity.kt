package com.meals.school_food.view.activity

import com.meals.school_food.R
import com.meals.school_food.base.BaseActivity
import com.meals.school_food.databinding.ActivityOpenSourceBinding
import com.meals.school_food.viewmodel.OpenSourceViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class OpenSourceActivity : BaseActivity<ActivityOpenSourceBinding, OpenSourceViewModel>() {

    override val viewModel: OpenSourceViewModel
        get() = getViewModel(OpenSourceViewModel::class)

    override val layoutRes: Int
        get() = R.layout.activity_open_source

    override fun observerViewModel() { }
}