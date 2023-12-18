package com.example.login

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.login.databinding.FragmentEditDialogBinding
import com.example.login.models.User

class EditDialogFragment(private val user: User, private val onEditUser: (User) -> Unit) :
    DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = FragmentEditDialogBinding.inflate(requireActivity().layoutInflater)

        binding.usernameEditText.setText(user.name)
        binding.passwordEditText.setText(user.contrasenna)

        return AlertDialog.Builder(requireActivity())
            .setTitle("Editar")
            .setView(binding.root)
            .setPositiveButton("Guardar") { _, _ ->
                val editedUser = user.copy(
                    name = binding.usernameEditText.text.toString(),
                    contrasenna = binding.passwordEditText.text.toString()
                )
                onEditUser(editedUser)
            }
            .setNegativeButton("Cancelar", null)
            .create()
    }
}
