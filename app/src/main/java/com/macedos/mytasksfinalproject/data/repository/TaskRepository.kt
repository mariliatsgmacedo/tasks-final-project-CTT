package com.macedos.mytasksfinalproject.data.repository

import androidx.lifecycle.LiveData
import com.macedos.mytasksfinalproject.data.AppDataBase
import com.macedos.mytasksfinalproject.data.dao.TaskDao
import com.macedos.mytasksfinalproject.data.model.Task

/**
 * @author Tamy Macedo
 * @see <a href="https://developer.android.com/codelabs/android-training-livedata-viewmodel#7">Room Codelab repository</a>
 */
class TaskRepository {
    private var taskDao:TaskDao
    private var mAllTasks: LiveData<List<Task>>

    init {
        val dataBase = AppDataBase.getDatabase()
        taskDao = dataBase.getTaskDao()
        mAllTasks = taskDao.fetch()
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return mAllTasks
    }
}