package com.example.focuswolf.data.repository

import com.example.focuswolf.data.dao.TaskDao
import com.example.focuswolf.data.entity.TaskEntity

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun getAllTasks(): List<TaskEntity> {
        return taskDao.getAllTasks()
    }

    suspend fun insertTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }
}