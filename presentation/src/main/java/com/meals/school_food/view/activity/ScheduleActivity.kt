package com.meals.school_food.view.activity

import android.os.Bundle
import com.meals.school_food.R
import com.meals.school_food.base.BaseActivity
import com.meals.school_food.databinding.ActivityScheduleBinding
import com.meals.school_food.viewmodel.ScheduleViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ScheduleActivity : BaseActivity<ActivityScheduleBinding, ScheduleViewModel>() {

    override val viewModel: ScheduleViewModel
        get() = getViewModel(ScheduleViewModel::class)

    override val layoutRes: Int
        get() = R.layout.activity_schedule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            viewModel.calendarClick(year, month, dayOfMonth)
        }
    }

    override fun observerViewModel() { }
}