package com.meals.school_food.view.activity

import androidx.lifecycle.Observer
import com.meals.school_food.R
import com.meals.school_food.base.BaseActivity
import com.meals.school_food.databinding.ActivitySearchBinding
import com.meals.school_food.viewmodel.SearchViewModel
import com.meals.school_food.widget.extension.startActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {

    override val viewModel: SearchViewModel
        get() = getViewModel(SearchViewModel::class)

    override val layoutRes: Int
        get() = R.layout.activity_search

    override fun observerViewModel() {
        with(viewModel) {
            searchEvent.observe(this@SearchActivity, Observer {
                getSchools()
                viewModel.isLoading.value = true
            })
            completeEvent.observe(this@SearchActivity, Observer {
                viewModel.isLoading.value = false
            })
            schoolAdapter.click.observe(this@SearchActivity, Observer {
                setSchoolData(application)
                startActivity(MainActivity::class.java)
            })
        }
    }
}