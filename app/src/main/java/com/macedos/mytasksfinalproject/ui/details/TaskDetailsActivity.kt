package com.macedos.mytasksfinalproject.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.databinding.ActivityTaskDetailsBinding

class TaskDetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityTaskDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_details)

    }
}