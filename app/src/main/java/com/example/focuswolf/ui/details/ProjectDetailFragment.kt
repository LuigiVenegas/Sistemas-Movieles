package com.example.focuswolf.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.focuswolf.R
import com.example.focuswolf.adapter.TaskAdapter
import com.example.focuswolf.data.FakeData

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.focuswolf.ui.dialog.NewTaskDialogFragment

class ProjectDetailFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var filteredTasks: MutableList<com.example.focuswolf.model.Task>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_project_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val projectName = arguments?.getString("projectName") ?: "Proyecto"

        val tvProjectName = view.findViewById<TextView>(R.id.tvProjectName)
        val recyclerProjectTasks = view.findViewById<RecyclerView>(R.id.recyclerProjectTasks)

        val fabAddProjectTask = view.findViewById<FloatingActionButton>(R.id.fabAddProjectTask)

        fabAddProjectTask.setOnClickListener {
            val dialog = NewTaskDialogFragment()

            dialog.forcedProjectName = projectName

            dialog.onTaskCreated = { newTask ->
                FakeData.tasks.add(newTask)

                filteredTasks.clear()
                filteredTasks.addAll(FakeData.tasks.filter { it.project == projectName })

                taskAdapter.notifyDataSetChanged()
            }

            dialog.show(parentFragmentManager, "NewProjectTaskDialog")
        }

        tvProjectName.text = projectName

        filteredTasks = FakeData.tasks
            .filter { it.project == projectName }
            .toMutableList()

        taskAdapter = TaskAdapter(
            filteredTasks,
            onEditClick = { task ->
                val dialog = NewTaskDialogFragment()

                dialog.taskToEdit = task
                dialog.forcedProjectName = projectName

                dialog.onTaskUpdated = { oldTask, updatedTask ->
                    val indexGlobal = FakeData.tasks.indexOf(oldTask)
                    if (indexGlobal != -1) {
                        FakeData.tasks[indexGlobal] = updatedTask
                    }

                    val indexLocal = filteredTasks.indexOf(oldTask)
                    if (indexLocal != -1) {
                        filteredTasks[indexLocal] = updatedTask
                        taskAdapter.notifyItemChanged(indexLocal)
                    }
                }

                dialog.show(parentFragmentManager, "EditProjectTaskDialog")
            },
            onDeleteClick = { task ->
                FakeData.tasks.remove(task)

                val indexLocal = filteredTasks.indexOf(task)
                if (indexLocal != -1) {
                    filteredTasks.removeAt(indexLocal)
                    taskAdapter.notifyItemRemoved(indexLocal)
                }
            }
        )

        recyclerProjectTasks.layoutManager = LinearLayoutManager(requireContext())
        recyclerProjectTasks.adapter = taskAdapter
    }

}