package com.example.focuswolf.model

data class Task(
    val title: String,
    val project: String,
    val dueDate: String,
    val priority: String,
    val state: String,
    val completed: Boolean = false
)
