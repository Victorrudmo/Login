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

        // Inicializar UserRepository
        userRepository = UserRepository

        // Configurar el botón de login
        binding.loginButton.setOnClickListener {
            // Obtener las credenciales desde los EditText utilizando View Binding
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Intentar hacer login
            if (userRepository.login(username, password)) {
                val userController = userRepository.getUserController()

                // Crear un Intent para la nueva Activity
                val userListIntent = Intent(this, UserListActivity::class.java)

                // Pasa la lista de usuarios a la nueva Activity
                userListIntent.putExtra("userList", userController.getAllUsers() as ArrayList<User>)

                // Inicia la nueva Activity
                startActivity(userListIntent)

                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
            } else {
                // El login falla
                Toast.makeText(this, "Login fallido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}




