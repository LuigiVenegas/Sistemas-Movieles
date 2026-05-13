package com.example.focuswolf.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.focuswolf.model.Task

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = ProjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["projectId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["projectId"])]
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val projectId: Int,
    val dueDate: String,
    val priority: String,
    val state: String,
    val completed: Boolean
)

fun TaskEntity.toTask(projectName: String = ""): Task {
    return Task(
        id = id,
        title = title,
        projectId = projectId,
        project = projectName,
        dueDate = dueDate,
        priority = priority,
        state = state,
        completed = completed
    )
}