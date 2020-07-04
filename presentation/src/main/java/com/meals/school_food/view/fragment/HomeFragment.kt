package com.meals.school_food.view.fragment

import androidx.lifecycle.Observer
import com.meals.school_food.R
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.FragmentHomeBinding
import com.meals.school_food.view.activity.SearchActivity
import com.meals.school_food.viewmodel.HomeViewModel
import com.meals.school_food.widget.extension.getColor
import com.meals.school_food.widget.extension.startActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel
        get() = getViewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun observerViewModel() {
        with(viewModel) {
            searchEvent.observe(this@HomeFragment, Observer {
                startActivity(SearchActivity::class.java)
            })
            morningEvent.observe(this@HomeFragment, Observer {
                with(binding) {
                    recyclerView.adapter = morningAdapter
                    time.value = getString(R.string.morning)
                    morning.setBackgroundResource(R.drawable.round_primary)
                    morning.setTextColor(getColor(R.color.colorWhite))
                    lunch.setBackgroundResource(R.drawable.round_border)
                    lunch.setTextColor(getColor(R.color.colorBlack))
                    diner.setBackgroundResource(R.drawable.round_border)
                    diner.setTextColor(getColor(R.color.colorBlack))
                }
            })
            lunchEvent.observe(this@HomeFragment, Observer {
                with(binding) {
                    recyclerView.adapter = lunchAdapter
                    time.value = getString(R.string.lunch)
                    morning.setBackgroundResource(R.drawable.round_border)
                    morning.setTextColor(getColor(R.color.colorBlack))
                    lunch.setBackgroundResource(R.drawable.round_primary)
                    lunch.setTextColor(getColor(R.color.colorWhite))
                    diner.setBackgroundResource(R.drawable.round_border)
                    diner.setTextColor(getColor(R.color.colorBlack))
                }
            })
            dinerEvent.observe(this@HomeFragment, Observer {
                with(binding) {
                    recyclerView.adapter = dinnerAdapter
                    time.value = getString(R.string.diner)
                    morning.setBackgroundResource(R.drawable.round_border)
                    morning.setTextColor(getColor(R.color.colorBlack))
                    lunch.setBackgroundResource(R.drawable.round_border)
                    lunch.setTextColor(getColor(R.color.colorBlack))
                    diner.setBackgroundResource(R.drawable.round_primary)
                    diner.setTextColor(getColor(R.color.colorWhite))
                }
            })
        }
    }
}