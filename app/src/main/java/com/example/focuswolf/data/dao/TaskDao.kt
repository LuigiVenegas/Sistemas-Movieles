package com.example.focuswolf.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.focuswolf.data.entity.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskEntity>

    @Query("SELECT * FROM tasks WHERE project = :projectName")
    suspend fun getTasksByProject(projectName: String): List<TaskEntity>

    @Insert
    suspend fun insertTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}