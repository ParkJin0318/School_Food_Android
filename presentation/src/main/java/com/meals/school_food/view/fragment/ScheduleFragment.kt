package com.meals.school_food.view.fragment

import com.meals.school_food.R
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.FragmentScheduleBinding
import com.meals.school_food.viewmodel.ScheduleViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ScheduleFragment : BaseFragment<FragmentScheduleBinding, ScheduleViewModel>() {

    override val viewModel: ScheduleViewModel
        get() = getViewModel(ScheduleViewModel::class)

    override val layoutRes: Int
        get() = R.layout.fragment_schedule

    override fun observerViewModel() { }
}