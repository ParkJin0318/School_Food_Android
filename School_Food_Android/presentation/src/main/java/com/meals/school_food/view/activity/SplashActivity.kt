package com.meals.school_food.view.activity

import androidx.lifecycle.Observer
import com.meals.school_food.R
import com.meals.school_food.base.BaseActivity
import com.meals.school_food.databinding.ActivitySplashBinding
import com.meals.school_food.viewmodel.SplashViewModel
import com.meals.school_food.widget.extension.startActivityWithFinish
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel
        get() = getViewModel(SplashViewModel::class)

    override val layoutRes: Int
        get() = R.layout.activity_splash

    override fun observerViewModel() {
        with(viewModel) {
            onSuccessEvent.observe(this@SplashActivity, Observer {
                startActivityWithFinish(MainActivity::class.java)
            })
            onErrorEvent.observe(this@SplashActivity, Observer {
                startActivityWithFinish(SearchActivity::class.java)
            })
        }
    }
}