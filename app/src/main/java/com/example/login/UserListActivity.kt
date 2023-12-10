package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.adapter.UserAdapter
import com.example.login.controller.UserController
import com.example.login.dao.UserDao
import com.example.login.databinding.ActivityUserListBinding
import com.example.login.models.User

class UserListActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var binding: ActivityUserListBinding
    private lateinit var userList: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recibe la lista de usuarios desde el Intent
        userList = (intent.getSerializableExtra("userList") as ArrayList<User>).toMutableList()

        // Configura el RecyclerView con el nuevo diseño y el Adapter
        userAdapter = UserAdapter(userList) { position ->
            deleteUser(position)
        }

        binding.myRecyclerView.adapter = userAdapter
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)

        // Desactivar animaciones de intercambio de elementos
        binding.myRecyclerView.itemAnimator = null
    }

    private fun deleteUser(position: Int) {
        if (position in 0 until userList.size) {
            val userToDelete = userList[position]

            // Eliminar el usuario de la lista
            val userController = UserController(UserDao())
            userController.deleteUser(userToDelete)

            // Actualizar la lista y la vista
            userList.removeAt(position)
            userAdapter.notifyItemRemoved(position)
        }
    }
}


