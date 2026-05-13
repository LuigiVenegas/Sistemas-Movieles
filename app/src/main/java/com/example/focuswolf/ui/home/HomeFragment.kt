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
import com.example.focuswolf.data.entity.ProjectEntity


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

    private fun seedDefaultProjects() {
        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            val projectDao = db.projectDao()

            if (projectDao.countProjects() == 0) {
                projectDao.insertProject(ProjectEntity(name = "Sistemas Móviles"))
                projectDao.insertProject(ProjectEntity(name = "Diseño UX"))
                projectDao.insertProject(ProjectEntity(name = "Universidad"))
                projectDao.insertProject(ProjectEntity(name = "FocusWolf App"))
            }
        }
    }

    private val sampleTask = mutableListOf(
        Task(
            title = "...",
            projectId = 1,
            project = "...",
            dueDate = "...",
            priority = "...",
            state = "...",
            completed = false
        )
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

        seedDefaultProjects()

        recyclerTasks.layoutManager = LinearLayoutManager(requireContext())
        taskAdapter = TaskAdapter(
            FakeData.tasks,
            onEditClick = { task ->
                val dialog = NewTaskDialogFragment()

                dialog.taskToEdit = task

                dialog.onTaskUpdated = { oldTask, updatedTask ->
                    taskAdapter.updateTask(oldTask, updatedTask)

                    lifecycleScope.launch {

                        repository.updateTask(
                            TaskEntity(
                                id = oldTask.id,
                                title = updatedTask.title,
                                projectId = updatedTask.projectId,
                                dueDate = updatedTask.dueDate,
                                priority = updatedTask.priority,
                                state = updatedTask.state,
                                completed = updatedTask.completed
                            )
                        )
                    }

                }

                dialog.show(parentFragmentManager, "EditTaskDialog")
            },
            onDeleteClick = {
                taskAdapter.removeTask(it)
                lifecycleScope.launch {

                    repository.deleteTask(
                        TaskEntity(
                            id = it.id,
                            title = it.title,
                            projectId = it.projectId,
                            dueDate = it.dueDate,
                            priority = it.priority,
                            state = it.state,
                            completed = it.completed
                        )
                    )
                }
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
                            id = newTask.id,
                            title = newTask.title,
                            projectId = newTask.projectId,
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
