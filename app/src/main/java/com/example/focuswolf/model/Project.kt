package com.example.focuswolf.model

data class Project(
    val name: String,
    val totalTasks: Int,
    val completedTasks: Int
) {
    val progressPercent: Int
        get() = if (totalTasks == 0) 0 else (completedTasks * 100) / totalTasks
}
