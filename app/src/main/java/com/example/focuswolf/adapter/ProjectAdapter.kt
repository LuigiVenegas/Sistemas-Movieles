package com.example.focuswolf.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.focuswolf.R
import com.example.focuswolf.model.Project

class ProjectAdapter(
    private val projects: List<Project>,
    private val onProjectClick: ((Project) -> Unit)? = null
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProjectName: TextView = itemView.findViewById(R.id.tvProjectName)
        val progressProject: ProgressBar = itemView.findViewById(R.id.progressProject)
        val tvTasksCount: TextView = itemView.findViewById(R.id.tvTasksCount)
        val tvPercent: TextView = itemView.findViewById(R.id.tvPercent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projects[position]
        holder.tvProjectName.text = project.name
        holder.progressProject.progress = project.progressPercent
        holder.tvTasksCount.text = "Tareas ${project.totalTasks}"
        holder.tvPercent.text = "${project.progressPercent}%"
        holder.itemView.setOnClickListener { onProjectClick?.invoke(project) }
    }

    override fun getItemCount(): Int = projects.size
}
