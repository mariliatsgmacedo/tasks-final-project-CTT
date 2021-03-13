package com.macedos.mytasksfinalproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        var title: String,
        var description: String,
        var status: Int
) : Serializable {

    companion object {
        const val TODO = 1
        const val IN_PROGRESS = 2
        const val DONE = 3

    }
}
