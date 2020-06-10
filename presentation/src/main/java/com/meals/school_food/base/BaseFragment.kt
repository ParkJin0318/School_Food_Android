package com.meals.school_food.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.meals.school_food.BR

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM

    protected abstract val viewModel: VM
    protected abstract val layoutRes: Int

    protected abstract fun observerViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)!!
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelProvider()
        observerViewModel()
    }

    private fun viewModelProvider() {
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
        mBinding.setVariable(BR.viewModel, mViewModel)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }
}