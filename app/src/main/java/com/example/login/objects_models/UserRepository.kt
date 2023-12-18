package com.example.login.objects_models

import com.example.login.controller.UserController
import com.example.login.dao.UserDao
import com.example.login.interfaces.UserDaoInterface
import com.example.login.models.User

object UserRepository {
    private val userDao: UserDaoInterface = UserDao
    private val userController: UserController = UserController(userDao)

    fun getUserController(): UserController {
        return userController
    }

    fun login(username: String, password: String): Boolean {
        val registeredUsers = userDao.getAllUsers()

        val user = registeredUsers.find { it.name.equals(username, ignoreCase = true) && it.contrasenna == password }

        return user != null
    }

    fun register(username: String, password: String): Boolean {
        val registeredUsers = userDao.getAllUsers()

        if (registeredUsers.any { it.name.equals(username, ignoreCase = true) }) {
            // El usuario ya esta registrado
            return false
        }

        val newUser = User(registeredUsers.size + 1, username, password)
        userDao.addUser(newUser)

        return true
    }
}







