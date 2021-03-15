package com.macedos.mytasksfinalproject.ui.form

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.data.repository.TaskRepository

class TaskFormViewModel: ViewModel() {
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    private val taskRepository = TaskRepository()

    fun taskSave(){
        val mTitle = title.value?:run { return  }
        val mDescription = description.value?:run { return  }
        taskRepository.insert(Task(title = mTitle, description = mDescription))
    }

    fun updateTask(task: Task){
        taskRepository.update(task)
    }

    val formValid = MediatorLiveData<Boolean>().apply {
        addSource(title){
            value = isFormValid()
        }

        addSource(description){
            value = isFormValid()
        }
    }

    private fun isFormValid(): Boolean {
        val mTitle = title.value?:run { return false }
        val mDescription = description.value?:run { return false }

        return (mTitle.trim().isNotEmpty() && mDescription.trim().isNotEmpty())
    }
}