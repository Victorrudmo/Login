package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login.databinding.ActivityMainBinding
import com.example.login.models.User
import com.example.login.objects_models.UserRepository
class MainActivity : AppCompatActivity() {
    private lateinit var userRepository: UserRepository
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepository = UserRepository

        binding.loginButton.setOnClickListener {
            startLogin()
        }

        binding.registerButton.setOnClickListener {
            showRegisterDialog()
        }
    }

    private fun startLogin() {
        val username = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (userRepository.login(username, password)) {
            setUserLoggedIn(username)
            launchUserListActivity()
            Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Login fallido", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showRegisterDialog() {
        val dialog = RegisterDialogFragment { username, password ->
            if (userRepository.register(username, password)) {
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show(supportFragmentManager, "RegisterDialog")
    }

    private fun setUserLoggedIn(username: String) {

    }

    private fun launchUserListActivity() {
        val userController = userRepository.getUserController()
        val userListIntent = Intent(this, UserListActivity::class.java)
        userListIntent.putExtra("userList", userController.getAllUsers() as ArrayList<User>)
        startActivity(userListIntent)
        finish() // Cierra la actividad actual para que no pueda volver atrás
    }
}




