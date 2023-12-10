package com.example.login.interfaces

import com.example.login.models.User

interface UserDaoInterface {
    fun getAllUsers(): List<User>
    fun deleteUser(user: User)
}