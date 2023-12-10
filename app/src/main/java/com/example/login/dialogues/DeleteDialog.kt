package com.example.login.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DeleteDialog(private val onDeleteConfirmed: () -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Confirmar eliminación")
            .setMessage("¿Estás seguro de que deseas eliminar este usuario?")
            .setPositiveButton("Sí") { _, _ ->
                onDeleteConfirmed.invoke()
            }
            .setNegativeButton("No") { _, _ ->

            }
            .create()
    }
}
