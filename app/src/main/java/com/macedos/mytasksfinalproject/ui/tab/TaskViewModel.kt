package com.macedos.mytasksfinalproject.ui.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.data.repository.TaskRepository

class TaskViewModel: ViewModel() {
    var taskList = MutableLiveData<List<Task>>()

    private val repository: TaskRepository = TaskRepository()
    private val allTasks: LiveData<List<Task>> = repository.getAllTasks()

    fun getAllTasks(): LiveData<List<Task>> {
        return allTasks
    }

}