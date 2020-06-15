package com.meals.school_food.view.activity

import com.meals.school_food.R
import com.meals.school_food.base.BaseActivity
import com.meals.school_food.databinding.ActivitySearchBinding
import com.meals.school_food.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {

    override val viewModel: SearchViewModel
        get() = getViewModel(SearchViewModel::class)

    override val layoutRes: Int
        get() = R.layout.activity_search

    override fun init() { }

    override fun observerViewModel() {
        with(viewModel) {

        }
    }

}