package com.macedos.mytasksfinalproject.ui.tab

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.macedos.mytasksfinalproject.data.model.Task

class TaskViewModel: ViewModel() {
    var taskList = MutableLiveData<List<Task>>()
}