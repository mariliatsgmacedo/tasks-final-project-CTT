package com.macedos.mytasksfinalproject.data.repository

import androidx.lifecycle.LiveData
import com.macedos.mytasksfinalproject.data.AppDataBase
import com.macedos.mytasksfinalproject.data.dao.TaskDao
import com.macedos.mytasksfinalproject.data.model.Task
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author Tamy Macedo
 * @see <a href="https://developer.android.com/codelabs/android-training-livedata-viewmodel#7">Room Codelab repository</a>
 */
class TaskRepository {
    private var taskDao:TaskDao
    private var mAllTasks: LiveData<List<Task>>
    private val executorService:ExecutorService

    init {
        val dataBase = AppDataBase.getDatabase()
        taskDao = dataBase.getTaskDao()
        mAllTasks = taskDao.fetch()
        val numberCore = Runtime.getRuntime().availableProcessors()
        executorService = Executors.newFixedThreadPool(numberCore)
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return mAllTasks
    }

    fun insert(task: Task){
        executorService.execute {
            taskDao.insert(task)
        }
    }
    fun delete(task: Task){
        executorService.execute {
            taskDao.delete(task)
        }
    }

}