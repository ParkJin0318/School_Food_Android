package com.meals.school_food.view.activity

import com.meals.school_food.R
import com.meals.school_food.base.BaseActivity
import com.meals.school_food.databinding.ActivityVersionBinding
import com.meals.school_food.viewmodel.VersionViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class VersionActivity : BaseActivity<ActivityVersionBinding, VersionViewModel>() {

    override val viewModel: VersionViewModel
        get() = getViewModel(VersionViewModel::class)

    override val layoutRes: Int
        get() = R.layout.activity_version

    override fun observerViewModel() {
        with(viewModel) {
            backEvent.observe(::getLifecycle) {
                onBackPressed()
            }
        }
    }
}