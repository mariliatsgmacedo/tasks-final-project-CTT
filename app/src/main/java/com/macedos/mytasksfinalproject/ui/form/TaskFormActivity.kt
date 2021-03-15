package com.macedos.mytasksfinalproject.ui.form


import android.os.Bundle
import com.macedos.mytasksfinalproject.BR
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.databinding.ActivityTaskFormBinding
import com.macedos.mytasksfinalproject.ui.base.BaseActivity

class TaskFormActivity: BaseActivity<ActivityTaskFormBinding, TaskFormViewModel>() {

    override fun setViewConfiguration(): Triple<Int, Int, Class<TaskFormViewModel>> {
        return Triple(R.layout.activity_task_form, BR.viewModel,TaskFormViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.buttonAdd.setOnClickListener {
            viewModel.taskSave()
            finish()
        }
    }

}