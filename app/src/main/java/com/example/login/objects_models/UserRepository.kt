package com.example.login.objects_models

import com.example.login.controller.UserController
import com.example.login.dao.UserDao
import com.example.login.interfaces.UserDaoInterface
import com.example.login.models.User

object UserRepository {
    private val userDao: UserDaoInterface = UserDao()
    private val userController: UserController = UserController(userDao)

    fun getUserController(): UserController {
        return userController
    }

    fun login(username: String, password: String): Boolean {
        // lista de usuario registrados
        val registeredUsers = listOf(
            User(1, "Usuario1", "contrasenna1"),
            User(2, "Usuario2", "contrasenna2")
        )

        // Verificar si las credenciales coinciden con algún usuario registrado
        val user = registeredUsers.find { it.name.equals(username, ignoreCase = true) && it.contrasenna == password }

        // Devolver true si se encuentra el usuario, indicando un inicio de sesión exitoso
        return user != null
    }

}


