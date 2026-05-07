package com.example.focuswolf.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.focuswolf.R
import com.example.focuswolf.model.Task

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onEditClick: ((Task) -> Unit)? = null,
    private val onDeleteClick: ((Task) -> Unit)? = null
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rbTask: RadioButton = itemView.findViewById(R.id.rbTask)
        val tvTaskTitle: TextView = itemView.findViewById(R.id.tvTaskTitle)
        val tvProjectName: TextView = itemView.findViewById(R.id.tvProjectName)
        val tvPriority: TextView = itemView.findViewById(R.id.tvPriority)
        val tvDueDate: TextView = itemView.findViewById(R.id.tvDueDate)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.rbTask.isChecked = task.completed
        holder.tvTaskTitle.text = task.title
        holder.tvProjectName.text = task.project
        holder.tvPriority.text = task.priority
        holder.tvDueDate.text = task.dueDate
        holder.tvStatus.text = task.state
        holder.btnEdit.setOnClickListener { onEditClick?.invoke(task) }
        holder.btnDelete.setOnClickListener { onDeleteClick?.invoke(task) }
    }

    override fun getItemCount(): Int = tasks.size

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    fun removeTask(task: Task) {
        val index = tasks.indexOf(task)
        if (index != -1) {
            tasks.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun updateTask(oldTask: Task, newTask: Task) {
        val index = tasks.indexOf(oldTask)
        if (index != -1) {
            tasks[index] = newTask
            notifyItemChanged(index)
        }
    }
}
