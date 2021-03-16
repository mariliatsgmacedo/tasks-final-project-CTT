package com.macedos.mytasksfinalproject.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.macedos.mytasksfinalproject.BR
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.config.AppConstants
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.data.model.Task.Companion.TODO
import com.macedos.mytasksfinalproject.databinding.ActivityTaskDetailsBinding
import com.macedos.mytasksfinalproject.ui.base.BaseActivity
import com.macedos.mytasksfinalproject.ui.form.TaskFormActivity

class TaskDetailsActivity : BaseActivity<ActivityTaskDetailsBinding, TaskDetailsViewModel>() {

    private lateinit var task: Task

    override fun setViewConfiguration(): Triple<Int, Int, Class<TaskDetailsViewModel>> {
        return Triple(
            R.layout.activity_task_details,
            BR.viewModel,
            TaskDetailsViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        task = intent.getSerializableExtra(AppConstants.KEY_TASK) as Task
        updateInfo()

        binding.editIcon.setOnClickListener {
            startFormActivity()
        }

        binding.buttonDeleteTask.setOnClickListener {
            viewModel.delete(task)
            finish()
        }

        binding.buttonStart.setOnClickListener {
            updateTask()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent()
                intent.putExtra("task", task)
                setResult(1, intent)
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            (data?.getSerializableExtra(AppConstants.KEY_TASK) as? Task)?.let {
                task = it
                updateInfo()
            }
        }
    }

    private fun updateInfo() {
        viewModel.title.value = task.title
        viewModel.description.value = task.description
        binding.subtitleScreenDetails.text = getStatusName()
        buttonConfiguration()
    }

    private fun startFormActivity() {
        val intent = Intent(this, TaskFormActivity::class.java)
        intent.putExtra(AppConstants.KEY_TASK, task)
        startActivityForResult(intent, 1)
    }

    private fun getStatusName(): String {
        return when (task.status) {
            Task.TODO -> getString(R.string.todo_tabbar)
            Task.IN_PROGRESS -> getString(R.string.in_progress_tabbar)
            Task.DONE -> getString(R.string.done_tabbar)
            else -> "Invalid"
        }
    }

    private fun buttonConfiguration() {
        binding.buttonStart.visibility = View.VISIBLE
        when (task.status) {

            Task.IN_PROGRESS -> {
                binding.buttonStart.text = getString(R.string.finish_task)
            }
            Task.DONE -> {
                getString(R.string.done_tabbar)
                binding.buttonStart.visibility = View.GONE
            }
        }
    }

    private fun updateTask() {
        when (task.status) {
            Task.TODO -> {
                task.status = Task.IN_PROGRESS
            }
            Task.IN_PROGRESS -> {
                task.status = Task.DONE
            }
        }

        viewModel.update(task)
        finish()
    }

}