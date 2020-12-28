package com.meals.school_food.view.activity

import com.meals.school_food.R
import com.meals.school_food.base.BaseActivity
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.ActivityScheduleBinding
import com.meals.school_food.viewmodel.ScheduleViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ScheduleActivity : BaseActivity<ActivityScheduleBinding, ScheduleViewModel>() {

    override val viewModel: ScheduleViewModel
        get() = getViewModel(ScheduleViewModel::class)

    override val layoutRes: Int
        get() = R.layout.activity_schedule

    override fun observerViewModel() {
        with(viewModel) {
            binding.calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
                calendarClick(year, month, dayOfMonth)
            }
        }
    }
}