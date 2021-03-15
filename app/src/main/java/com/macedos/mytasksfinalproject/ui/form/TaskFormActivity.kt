package com.macedos.mytasksfinalproject.ui.form


import com.macedos.mytasksfinalproject.BR
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.databinding.ActivityTaskFormBinding
import com.macedos.mytasksfinalproject.ui.base.BaseActivity

class TaskFormActivity: BaseActivity<ActivityTaskFormBinding, TaskFormViewModel>() {

    override fun setViewConfiguration(): Triple<Int, Int, Class<TaskFormViewModel>> {
        return Triple(R.layout.activity_task_details, BR.viewModel,TaskFormViewModel::class.java)
    }

}