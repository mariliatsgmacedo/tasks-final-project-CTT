package com.macedos.mytasksfinalproject.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<B: ViewDataBinding, VM: ViewModel> : AppCompatActivity(){
    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    abstract fun setViewConfiguration(): Triple<Int, Int, Class<VM>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performBinding()
    }

    private fun performBinding(){
        val (layoutID, viewModelVariableID, viewModelClass) = setViewConfiguration()
        binding = DataBindingUtil.setContentView(this,layoutID)
        binding.apply {
            this@BaseActivity.viewModel = ViewModelProvider(this@BaseActivity).get(viewModelClass)
            setVariable(viewModelVariableID,this@BaseActivity.viewModel)
            executePendingBindings()
            lifecycleOwner = this@BaseActivity
        }
    }
}