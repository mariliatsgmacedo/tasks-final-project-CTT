package com.macedos.mytasksfinalproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.macedos.mytasksfinalproject.data.model.Task

@Dao
abstract class TaskDao {

    @Query("SELECT * FROM Task WHERE status = :status")
    abstract fun findByStatus(status: Int): LiveData<List<Task>>

    @Insert
    abstract fun insert(task: Task)

    @Delete
    abstract fun delete(task: Task)


}