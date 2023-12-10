package com.example.login.dao

import com.example.login.interfaces.UserDaoInterface
import com.example.login.models.User

class UserDao : UserDaoInterface {
    private val userList = mutableListOf(
        User(1, "Usuario1", "contrasenna1"),
        User(2, "Usuario2", "contrasenna2")
    )

    override fun getAllUsers(): List<User> {
        return userList
    }

    override fun deleteUser(user: User) {
        userList.remove(user)
    }
}
