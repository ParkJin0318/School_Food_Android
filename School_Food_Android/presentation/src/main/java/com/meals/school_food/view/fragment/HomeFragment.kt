package com.meals.school_food.view.fragment

import androidx.lifecycle.Observer
import com.meals.school_food.R
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.FragmentHomeBinding
import com.meals.school_food.view.activity.MealActivity
import com.meals.school_food.view.activity.ScheduleActivity
import com.meals.school_food.view.activity.SearchActivity
import com.meals.school_food.viewmodel.HomeViewModel
import com.meals.school_food.widget.extension.getFormatDate
import com.meals.school_food.widget.extension.getTime
import com.meals.school_food.widget.extension.startActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel
        get() = getViewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun observerViewModel() {
        with(viewModel) {
            onScheduleDetailEvent.observe(this@HomeFragment, Observer {
                startActivity(ScheduleActivity::class.java)
            })
            onMealDetailEvent.observe(this@HomeFragment, Observer {
                startActivity(MealActivity::class.java)
            })
            mealInfo.observe(this@HomeFragment, Observer {
                val now = Date().getFormatDate()
                when {
                    now.before("08:20".getTime()) -> {
                        time.value = getString(R.string.morning)
                        binding.mealImage.setImageResource(R.drawable.ic_breakfast)
                        mealText.value = it.breakfast
                    }
                    now.before("13:20".getTime()) -> {
                        time.value = getString(R.string.lunch)
                        binding.mealImage.setImageResource(R.drawable.ic_lunch)
                        mealText.value = it.lunch
                    }
                    else -> {
                        time.value = getString(R.string.diner)
                        binding.mealImage.setImageResource(R.drawable.ic_dinner)
                        mealText.value = it.dinner
                    }
                }
            })
        }
    }
}