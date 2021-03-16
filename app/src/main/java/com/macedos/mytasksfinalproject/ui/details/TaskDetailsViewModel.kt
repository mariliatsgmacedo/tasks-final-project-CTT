package com.macedos.mytasksfinalproject.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.data.repository.TaskRepository

class TaskDetailsViewModel: ViewModel() {
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    private val repository = TaskRepository()

    fun delete(task: Task){
        repository.delete(task)
    }
    fun update(task: Task){
        repository.update(task)
    }
}