package com.example.focuswolf.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.focuswolf.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val project: String,
    val dueDate: String,
    val priority: String,
    val state: String,
    val completed: Boolean
)

fun TaskEntity.toTask(): Task {
    return Task(
        title = title,
        project = project,
        dueDate = dueDate,
        priority = priority,
        state = state,
        completed = completed
    )
}