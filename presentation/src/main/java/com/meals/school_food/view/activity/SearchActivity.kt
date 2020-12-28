package com.meals.school_food.view.activity

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.meals.school_food.R
import com.meals.school_food.base.BaseActivity
import com.meals.school_food.databinding.ActivitySearchBinding
import com.meals.school_food.viewmodel.SearchViewModel
import com.meals.school_food.widget.extension.startActivityWithFinish
import com.meals.school_food.widget.extension.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {

    override val viewModel: SearchViewModel
        get() = getViewModel(SearchViewModel::class)

    override val layoutRes: Int
        get() = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSearchEvent()
    }

    override fun observerViewModel() {
        with(viewModel) {
            searchEvent.observe(this@SearchActivity, Observer {
                getSchools()
                isLoading.value = true
            })
            schoolAdapter.click.observe(this@SearchActivity, Observer {
                setSchoolInfo()
            })
            onSuccessEvent.observe(this@SearchActivity, Observer {
                startActivityWithFinish(MainActivity::class.java)
            })
            onErrorEvent.observe(this@SearchActivity, Observer {
                toast(it.toString())
            })
        }
    }

    private fun onSearchEvent() {
        binding.searchEditText.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.searchClick()

                val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)
                binding.searchEditText.clearFocus()

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}