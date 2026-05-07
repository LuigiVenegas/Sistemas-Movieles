    package com.example.focuswolf.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.focuswolf.R
import com.example.focuswolf.adapter.TaskAdapter
import com.example.focuswolf.model.Task
import com.example.focuswolf.ui.dialog.NewTaskDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.focuswolf.data.FakeData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.focuswolf.data.AppDatabase
import com.example.focuswolf.data.entity.TaskEntity
import com.example.focuswolf.data.repository.TaskRepository
import com.example.focuswolf.data.entity.toTask



private lateinit var taskAdapter: TaskAdapter
class HomeFragment : Fragment() {

    private lateinit var repository: TaskRepository

    private fun loadTasksFromDatabase() {
        lifecycleScope.launch {
            val tasksFromDb = repository.getAllTasks()

            FakeData.tasks.clear()
            FakeData.tasks.addAll(tasksFromDb.map { it.toTask() })

            taskAdapter.notifyDataSetChanged()
        }
    }

    private val sampleTask = mutableListOf(
    Task("Preparar presentación", "Sistemas Móviles", "23 feb", "P Alta", "Pendiente"),
    Task("Terminar mockup", "Diseño UX", "25 feb", "P Media", "En progreso"),
    Task("Subir evidencia", "Universidad", "27 feb", "P Alta", "Pendiente")
    )

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerTasks = view.findViewById<RecyclerView>(R.id.recyclerTasks)
        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fabAdd)
        val db = AppDatabase.getDatabase(requireContext())
        repository = TaskRepository(db.taskDao())

        recyclerTasks.layoutManager = LinearLayoutManager(requireContext())
        taskAdapter = TaskAdapter(
            FakeData.tasks,
            onEditClick = { task ->
                val dialog = NewTaskDialogFragment()

                dialog.taskToEdit = task

                dialog.onTaskUpdated = { oldTask, updatedTask ->
                    taskAdapter.updateTask(oldTask, updatedTask)
                }

                dialog.show(parentFragmentManager, "EditTaskDialog")
            },
            onDeleteClick = {
                taskAdapter.removeTask(it)
            }
        )

        recyclerTasks.layoutManager = LinearLayoutManager(requireContext())
        recyclerTasks.adapter = taskAdapter
        loadTasksFromDatabase()


        fabAdd.setOnClickListener {
            val dialog = NewTaskDialogFragment()

            dialog.onTaskCreated = { newTask ->

                taskAdapter.addTask(newTask)

                lifecycleScope.launch {

                    repository.insertTask(
                        TaskEntity(
                            title = newTask.title,
                            project = newTask.project,
                            dueDate = newTask.dueDate,
                            priority = newTask.priority,
                            state = newTask.state,
                            completed = newTask.completed
                        )
                    )
                }
            }

            dialog.show(parentFragmentManager, "NewTaskDialog")
        }
    }
}
