package com.example.focuswolf.model

data class Task(
    val id: Int = 0,
    val title: String,
    val projectId: Int = 1,
    val project: String = "",
    val dueDate: String,
    val priority: String,
    val state: String,
    val completed: Boolean = false
)