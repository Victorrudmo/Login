package com.example.login.dao

import com.example.login.interfaces.UserDaoInterface
import com.example.login.models.User

object UserDao : UserDaoInterface {
    val userList = mutableListOf(
        User(1, "Usuario1", "contrasenna1"),
        User(2, "Usuario2", "contrasenna2")
    )

    override fun getAllUsers(): List<User> {
        return userList
    }

    override fun deleteUser(user: User) {
        userList.remove(user)
    }

    override fun editUser(user: User) {
        val existingUser = userList.find { it.id == user.id }
        existingUser?.apply {
            name = user.name
            contrasenna = user.contrasenna
        }
    }

    override fun addUser(user: User) {
        userList.add(user)
    }
}




