package com.example.login

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.login.databinding.FragmentRegisterDialogBinding
class RegisterDialogFragment(private val onRegister: (String, String) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = FragmentRegisterDialogBinding.inflate(requireActivity().layoutInflater)

        return AlertDialog.Builder(requireActivity())
            .setTitle("Registrar")
            .setView(binding.root)
            .setPositiveButton("Registrar") { _, _ ->
                val username = binding.usernameEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                if (username.isNotBlank() && password.isNotBlank()) {
                    onRegister(username, password)
                } else {
                    Toast.makeText(requireContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()
    }
}






