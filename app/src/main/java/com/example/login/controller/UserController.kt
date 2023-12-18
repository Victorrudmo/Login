package com.example.login.controller

import com.example.login.interfaces.UserDaoInterface
import com.example.login.models.User

class UserController(private val userDao: UserDaoInterface) {

    fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    fun editUser(user: User) {
        userDao.editUser(user)
    }

    fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}


