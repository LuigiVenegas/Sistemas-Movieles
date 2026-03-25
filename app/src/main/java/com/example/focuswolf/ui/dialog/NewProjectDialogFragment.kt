package com.example.focuswolf.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.focuswolf.R

class NewProjectDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_new_project, null)

        return AlertDialog.Builder(requireContext())
            .setTitle("Nuevo Proyecto")
            .setView(view)
            .setPositiveButton("Crear") { _, _ ->
                // Aquí luego puedes crear el proyecto
            }
            .setNegativeButton("Cancelar", null)
            .create()
    }
}