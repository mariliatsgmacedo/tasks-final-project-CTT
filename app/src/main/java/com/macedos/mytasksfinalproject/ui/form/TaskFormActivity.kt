package com.macedos.mytasksfinalproject.ui.form


import android.content.Intent
import android.os.Bundle
import com.macedos.mytasksfinalproject.BR
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.config.AppConstants
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.databinding.ActivityTaskFormBinding
import com.macedos.mytasksfinalproject.ui.base.BaseActivity

class TaskFormActivity : BaseActivity<ActivityTaskFormBinding, TaskFormViewModel>() {

    private var task: Task? = null

    override fun setViewConfiguration(): Triple<Int, Int, Class<TaskFormViewModel>> {
        return Triple(R.layout.activity_task_form, BR.viewModel, TaskFormViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task = intent.getSerializableExtra(AppConstants.KEY_TASK) as? Task

        task?.let {
            viewModel.title.value = it.title
            viewModel.description.value = it.description

            viewModel.title.observe(this){ value ->
                it.title = value
            }
            viewModel.description.observe(this){ value ->
                it.description = value
            }

        }

        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.buttonAdd.setOnClickListener {
            task?.let {
                viewModel.updateTask(it)
                val intent = Intent()
                intent.putExtra(AppConstants.KEY_TASK,it)
                setResult(1,intent)
            } ?: run {
                viewModel.taskSave()
            }
            finish()
        }
    }

}