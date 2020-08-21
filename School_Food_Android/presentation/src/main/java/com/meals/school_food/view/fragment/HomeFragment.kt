package com.meals.school_food.view.fragment

import androidx.lifecycle.Observer
import com.meals.school_food.R
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.FragmentHomeBinding
import com.meals.school_food.view.activity.SearchActivity
import com.meals.school_food.viewmodel.HomeViewModel
import com.meals.school_food.widget.extension.startActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel
        get() = getViewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun init() { binding.teSchoolName.isSelected = true }

    override fun observerViewModel() {
        with(viewModel) {
            searchEvent.observe(this@HomeFragment, Observer {
                startActivity(SearchActivity::class.java)
            })

            isMorning.observe(this@HomeFragment, Observer {
                if (it == true) {
                    binding.recyclerView.adapter = morningAdapter
                    time.value = getString(R.string.morning)
                }
            })
            isLunch.observe(this@HomeFragment, Observer {
                if (it == true) {
                    binding.recyclerView.adapter = lunchAdapter
                    time.value = getString(R.string.lunch)
                }
            })
            isDinner.observe(this@HomeFragment, Observer {
                if (it == true) {
                    binding.recyclerView.adapter = dinnerAdapter
                    time.value = getString(R.string.diner)
                }
            })
        }
    }
}