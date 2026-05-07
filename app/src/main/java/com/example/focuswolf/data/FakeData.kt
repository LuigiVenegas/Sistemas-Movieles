package com.example.focuswolf.data

import com.example.focuswolf.model.Task

object FakeData {
    val tasks = mutableListOf(
        Task("Preparar presentación", "Sistemas Móviles", "23 feb", "P Alta", "Pendiente", false),
        Task("Terminar mockup", "Diseño UX", "25 feb", "P Media", "En progreso", false),
        Task("Subir evidencia", "Universidad", "27 feb", "P Alta", "Pendiente", false)
    )
}