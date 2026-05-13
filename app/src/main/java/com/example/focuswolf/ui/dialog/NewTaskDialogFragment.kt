package com.example.focuswolf.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.focuswolf.R
import com.example.focuswolf.model.Task

class NewTaskDialogFragment : DialogFragment() {

    var taskToEdit: Task? = null
    var onTaskUpdated: ((Task, Task) -> Unit)? = null
    var onTaskCreated: ((Task) -> Unit)? = null
    var forcedProjectName: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_new_task, null)

        val etTitle = view.findViewById<EditText>(R.id.etTitle)
        val etProject = view.findViewById<EditText>(R.id.etProject)
        val etDate = view.findViewById<EditText>(R.id.etDate)
        val rgPriority = view.findViewById<RadioGroup>(R.id.rgPriority)
        val rgState = view.findViewById<RadioGroup>(R.id.rgState)
        val task = taskToEdit

        forcedProjectName?.let {
            etProject.setText(it)
            etProject.isEnabled = false
        }

        if (task != null) {
            etTitle.setText(task.title)
            etProject.setText(task.project)
            etDate.setText(task.dueDate)

            when (task.priority) {
                "Alta", "P Alta" -> rgPriority.check(R.id.rbAlta)
                "Media", "P Media" -> rgPriority.check(R.id.rbMedia)
                "Baja", "P Baja" -> rgPriority.check(R.id.rbBaja)
            }

            when (task.state) {
                "Pendiente" -> rgState.check(R.id.rbPending)
                "En progreso" -> rgState.check(R.id.rbProgress)
                "Hecha" -> rgState.check(R.id.rbDone)
            }
        }

        return AlertDialog.Builder(requireContext())
            .setTitle("Nueva Tarea")
            .setView(view)
            .setPositiveButton(if (taskToEdit == null) "Crear" else "Guardar") { _, _ ->

                val selectedPriorityId = rgPriority.checkedRadioButtonId
                val selectedStateId = rgState.checkedRadioButtonId

                val priorityText = if (selectedPriorityId != -1) {
                    view.findViewById<RadioButton>(selectedPriorityId).text.toString()
                } else {
                    "Media"
                }

                val stateText = if (selectedStateId != -1) {
                    view.findViewById<RadioButton>(selectedStateId).text.toString()
                } else {
                    "Pendiente"
                }

                val projectName = forcedProjectName ?: etProject.text.toString()

                val newTask = Task(
                    title = etTitle.text.toString(),
                    projectId = 1,
                    project = projectName,
                    dueDate = etDate.text.toString(),
                    priority = priorityText,
                    state = stateText,
                    completed = taskToEdit?.completed ?: false
                )

                val oldTask = taskToEdit

                if (oldTask == null) {
                    onTaskCreated?.invoke(newTask)
                } else {
                    onTaskUpdated?.invoke(oldTask, newTask)
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()
    }
}
