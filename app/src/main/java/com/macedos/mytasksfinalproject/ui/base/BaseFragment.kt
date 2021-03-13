package com.macedos.mytasksfinalproject.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<B:ViewDataBinding, VM: ViewModel>: Fragment() {
    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    abstract fun setViewConfiguration(): Triple<Int, Int, Class<VM>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val (layoutID, viewModelVariableID, viewModelClass) = setViewConfiguration()
        binding = DataBindingUtil.inflate(inflater,layoutID,container,false)
        binding.apply {
            this@BaseFragment.viewModel = ViewModelProvider(requireActivity()).get(viewModelClass)
            setVariable(viewModelVariableID,this@BaseFragment.viewModel)
            executePendingBindings()
            lifecycleOwner = this@BaseFragment
        }

        return binding.root
    }


}