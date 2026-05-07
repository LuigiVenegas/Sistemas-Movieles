package com.example.focuswolf.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.focuswolf.R
import com.example.focuswolf.adapter.ProjectAdapter
import com.example.focuswolf.model.Project
import com.example.focuswolf.ui.dialog.NewProjectDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.focuswolf.data.FakeData

class ProjectsFragment : Fragment() {

    private val projectNames = listOf(
        "Sistemas Móviles",
        "Diseño UX",
        "Universidad",
        "FocusWolf App"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_projects, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val projects = projectNames.map { name ->

            val tasks = FakeData.tasks.filter { it.project == name }

            val total = tasks.size
            val completed = tasks.count { it.state == "Hecha" || it.completed }

            Project(name, total, completed)
        }

        val recyclerProjects = view.findViewById<RecyclerView>(R.id.recyclerProjects)
        val fabAddProject = view.findViewById<FloatingActionButton>(R.id.fabAddProject)

        recyclerProjects.layoutManager = LinearLayoutManager(requireContext())
        recyclerProjects.adapter = ProjectAdapter(projects) { project ->
            val fragment = ProjectDetailFragment()

            val bundle = Bundle()
            bundle.putString("projectName", project.name)
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

        

        fabAddProject.setOnClickListener {
            NewProjectDialogFragment().show(parentFragmentManager, "NewProjectDialog")
        }
    }
}