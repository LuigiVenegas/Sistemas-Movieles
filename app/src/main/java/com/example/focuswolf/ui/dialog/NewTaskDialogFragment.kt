package com.example.focuswolf.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.focuswolf.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NewTaskDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_new_task, null)
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(view)
            .create()

        view.findViewById<Button>(R.id.btnCancel).setOnClickListener {
            dismiss()
        }

        view.findViewById<Button>(R.id.btnCreate).setOnClickListener {
            Toast.makeText(requireContext(), "Tarea creada (demo)", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return dialog
    }
}
