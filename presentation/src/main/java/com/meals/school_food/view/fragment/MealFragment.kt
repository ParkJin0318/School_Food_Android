package com.meals.school_food.view.fragment

import androidx.lifecycle.Observer
import com.meals.school_food.R
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.FragmentMealBinding
import com.meals.school_food.view.activity.SearchActivity
import com.meals.school_food.viewmodel.MealViewModel
import com.meals.school_food.widget.extension.startActivity
import com.meals.school_food.widget.extension.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MealFragment : BaseFragment<FragmentMealBinding, MealViewModel>() {

    override val viewModel: MealViewModel
        get() = getViewModel(MealViewModel::class)

    override val layoutRes: Int
        get() = R.layout.fragment_meal

    override fun observerViewModel() {
        with(viewModel) {
            searchEvent.observe(this@MealFragment, Observer {
                startActivity(SearchActivity::class.java)
            })
            nullCheck.observe(this@MealFragment, Observer {
                toast(R.string.select_error)
            })
        }
    }
}