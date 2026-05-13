package com.example.focuswolf.data

import com.example.focuswolf.model.Task

object FakeData {
    val tasks = mutableListOf(
        Task(
            title = "Preparar presentación",
            projectId = 1,
            project = "Sistemas Móviles",
            dueDate = "23 feb",
            priority = "P Alta",
            state = "Pendiente",
            completed = false
        )
    )
}