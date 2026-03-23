package com.example.focuswolf.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.focuswolf.R
import com.example.focuswolf.adapter.ProjectAdapter
import com.example.focuswolf.model.Project
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProjectsFragment : Fragment() {

    private val sampleProjects = listOf(
        Project("Sistemas Móviles", 10, 5),
        Project("Diseño UX", 8, 6),
        Project("Universidad", 6, 2)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_projects, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerProjects = view.findViewById<RecyclerView>(R.id.recyclerProjects)
        val fabAddProject = view.findViewById<FloatingActionButton>(R.id.fabAddProject)

        recyclerProjects.layoutManager = LinearLayoutManager(requireContext())
        recyclerProjects.adapter = ProjectAdapter(sampleProjects) {
            Toast.makeText(requireContext(), "Abrir proyecto: ${it.name}", Toast.LENGTH_SHORT).show()
        }

        fabAddProject.setOnClickListener {
            Toast.makeText(requireContext(), "Aquí luego puedes crear un proyecto", Toast.LENGTH_SHORT).show()
        }
    }
}
