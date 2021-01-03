package com.meals.school_food.view.fragment

import com.meals.domain.model.TimeInfo
import com.meals.school_food.R
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.FragmentHomeBinding
import com.meals.school_food.view.activity.MealActivity
import com.meals.school_food.view.activity.ScheduleActivity
import com.meals.school_food.viewmodel.HomeViewModel
import com.meals.school_food.widget.extension.startActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel
        get() = getViewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun observerViewModel() {
        with(viewModel) {
            timeInfo.observe(::getLifecycle) {
                when (it) {
                    TimeInfo.BREAKFAST -> {
                        timeText.value = getString(R.string.breakfast)
                        binding.mealImage.setImageResource(R.drawable.ic_breakfast)
                    }
                    TimeInfo.LUNCH -> {
                        timeText.value = getString(R.string.lunch)
                        binding.mealImage.setImageResource(R.drawable.ic_lunch)
                    }
                    TimeInfo.DINNER -> {
                        timeText.value = getString(R.string.dinner)
                        binding.mealImage.setImageResource(R.drawable.ic_dinner)
                    }
                    else -> {
                        timeText.value = "오류"
                        binding.mealImage.setImageResource(R.drawable.ic_error)
                    }
                }
            }
            onScheduleDetailEvent.observe(::getLifecycle) {
                it.getContentIfNotHandled()?.let {
                    startActivity(ScheduleActivity::class.java)
                }
            }
            onMealDetailEvent.observe(::getLifecycle) {
                it.getContentIfNotHandled()?.let {
                    startActivity(MealActivity::class.java)
                }
            }
            onErrorEvent.observe(::getLifecycle) {
                it.getContentIfNotHandled()?.let { throwable ->
                    mealText.value = throwable.message
                    timeText.value = "오류"
                    binding.mealImage.setImageResource(R.drawable.ic_error)
                }
            }
        }
    }
}