package com.example.focuswolf.ui.projecttasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.focuswolf.R
import com.example.focuswolf.adapter.TaskAdapter
import com.example.focuswolf.model.Task

class ProjectTasksFragment : Fragment() {

    private val tasks = mutableListOf(
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_project_tasks, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerProjectTasks = view.findViewById<RecyclerView>(R.id.recyclerProjectTasks)
        recyclerProjectTasks.layoutManager = LinearLayoutManager(requireContext())
        recyclerProjectTasks.adapter = TaskAdapter(tasks)
    }
}
