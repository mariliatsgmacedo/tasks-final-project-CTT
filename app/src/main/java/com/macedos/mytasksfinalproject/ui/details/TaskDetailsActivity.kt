package com.macedos.mytasksfinalproject.ui.details

import com.macedos.mytasksfinalproject.BR
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.databinding.ActivityTaskDetailsBinding
import com.macedos.mytasksfinalproject.ui.base.BaseActivity

class TaskDetailsActivity: BaseActivity<ActivityTaskDetailsBinding,TaskDetailsViewModel>() {

    override fun setViewConfiguration(): Triple<Int, Int, Class<TaskDetailsViewModel>> {
        return Triple(R.layout.activity_task_details, BR.viewModel,TaskDetailsViewModel::class.java)
    }
}