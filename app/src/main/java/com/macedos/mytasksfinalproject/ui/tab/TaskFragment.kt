package com.macedos.mytasksfinalproject.ui.tab

import android.os.Bundle
import android.view.View
import com.macedos.mytasksfinalproject.BR
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.databinding.FragmentTaskBinding
import com.macedos.mytasksfinalproject.ui.base.BaseFragment

class TaskFragment(private val status:Int): BaseFragment<FragmentTaskBinding,TaskViewModel>(){

    override fun setViewConfiguration(): Triple<Int, Int, Class<TaskViewModel>> {
        return Triple(R.layout.fragment_task,BR.viewModel,TaskViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureView()
        
    }

    private fun configureView(){
        binding.buttonAdd.visibility = if(status == Task.TODO) View.VISIBLE else View.GONE
        when(status) {
            Task.TODO -> {
                binding.emptyMessage.text = getString(R.string.message_things_todo)
            }
            Task.IN_PROGRESS -> {
                binding.emptyMessage.text = getString(R.string.message_things_in_progress)
            }
            Task.DONE -> {
                binding.emptyMessage.text = getString(R.string.message_things_done)
            }
        }

    }

}