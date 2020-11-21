package com.meals.school_food.view.fragment

import androidx.lifecycle.Observer
import com.meals.domain.model.TimeInfo
import com.meals.school_food.R
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.FragmentHomeBinding
import com.meals.school_food.view.activity.MealActivity
import com.meals.school_food.view.activity.ScheduleActivity
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
            timeInfo.observe(this@HomeFragment, Observer {
                when (it) {
                    TimeInfo.BREAKFAST -> {
                        time.value = getString(R.string.breakfast)
                        binding.mealImage.setImageResource(R.drawable.ic_breakfast)
                    }
                    TimeInfo.LUNCH -> {
                        time.value = getString(R.string.lunch)
                        binding.mealImage.setImageResource(R.drawable.ic_lunch)
                    }
                    TimeInfo.DINNER -> {
                        time.value = getString(R.string.dinner)
                        binding.mealImage.setImageResource(R.drawable.ic_dinner)
                    }
                }
            })
        }
    }

}