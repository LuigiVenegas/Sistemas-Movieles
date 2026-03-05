package com.example.focuswolf

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val addTaskButton = findViewById<Button>(R.id.addTaskButton)
        val projectsButton = findViewById<Button>(R.id.projectsButton)
        val statsButton = findViewById<Button>(R.id.statsButton)

        addTaskButton.setOnClickListener {
            Toast.makeText(this, "Abrir pantalla de tarea", Toast.LENGTH_SHORT).show()
        }

        projectsButton.setOnClickListener {
            Toast.makeText(this, "Abrir proyectos", Toast.LENGTH_SHORT).show()
        }

        statsButton.setOnClickListener {
            Toast.makeText(this, "Abrir estadísticas", Toast.LENGTH_SHORT).show()
        }
    }
}