package com.meals.school_food.view.activity

import android.os.Handler
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

    private val handler = Handler()

    private val runnableMain = Runnable {
        startActivityWithFinish(MainActivity::class.java)
    }

    private val runnableSearch = Runnable {
        startActivityWithFinish(SearchActivity::class.java)
    }

    override fun observerViewModel() {
        with(viewModel) {
            onSuccessEvent.observe(::getLifecycle) {
                handler.postDelayed(runnableMain, 1000)
            }
            onErrorEvent.observe(::getLifecycle) {
                handler.postDelayed(runnableSearch, 1000)
            }
        }
    }
}