package com.macedos.mytasksfinalproject.ui.form

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.databinding.ActivityTaskFormBinding

class TaskFormActivity: AppCompatActivity() {
    private lateinit var binding: ActivityTaskFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_form)


    }
}