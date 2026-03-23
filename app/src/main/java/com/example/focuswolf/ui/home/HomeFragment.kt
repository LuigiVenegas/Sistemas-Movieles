package com.example.focuswolf.ui.home

import android.os.Bundle
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

class HomeFragment : Fragment() {

    private val sampleTasks = listOf(
        Task("Preparar presentación", "Sistemas Móviles", "23 feb", "P Alta", "Pendiente"),
        Task("Terminar mockup", "Diseño UX", "25 feb", "P Media", "En progreso"),
        Task("Subir evidencia", "Universidad", "27 feb", "P Alta", "Pendiente")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerTasks = view.findViewById<RecyclerView>(R.id.recyclerTasks)
        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fabAdd)

        recyclerTasks.layoutManager = LinearLayoutManager(requireContext())
        recyclerTasks.adapter = TaskAdapter(
            sampleTasks,
            onEditClick = { Toast.makeText(requireContext(), "Editar: ${it.title}", Toast.LENGTH_SHORT).show() },
            onDeleteClick = { Toast.makeText(requireContext(), "Eliminar: ${it.title}", Toast.LENGTH_SHORT).show() }
        )

        fabAdd.setOnClickListener {
            NewTaskDialogFragment().show(parentFragmentManager, "NewTaskDialog")
        }
    }
}
