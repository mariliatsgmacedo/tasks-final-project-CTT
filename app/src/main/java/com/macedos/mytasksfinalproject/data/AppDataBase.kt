package com.macedos.mytasksfinalproject.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.macedos.mytasksfinalproject.MyApplication
import com.macedos.mytasksfinalproject.data.dao.TaskDao
import com.macedos.mytasksfinalproject.data.model.Task


@Database(
    version = 1,
    exportSchema = false,
    entities = [
        Task::class
    ]
)

abstract class AppDataBase: RoomDatabase() {
    abstract fun getTaskDao():TaskDao

    companion object{
        private const val DATABASE_NAME = "app-my-tasks"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(): AppDataBase {
            synchronized(this){
                val tempInstance = INSTANCE
                if (tempInstance != null){
                    return tempInstance
                }
                val instance = Room.databaseBuilder(
                    MyApplication.getContext(),
                    AppDataBase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}