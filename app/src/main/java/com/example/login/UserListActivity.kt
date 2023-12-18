package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.adapter.UserAdapter
import com.example.login.controller.UserController
import com.example.login.dao.UserDao
import com.example.login.databinding.ActivityUserListBinding
import com.example.login.models.User
import com.example.login.objects_models.UserRepository

class UserListActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var binding: ActivityUserListBinding
    private lateinit var userList: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userList = (intent.getSerializableExtra("userList") as ArrayList<User>).toMutableList()

        userAdapter = UserAdapter(userList,
            onDeleteClick = { position -> deleteUser(position) },
            onEditClick = { user -> showEditDialog(user) }
        )


        binding.myRecyclerView.adapter = userAdapter
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.myRecyclerView.itemAnimator = null

        binding.btnAdd.setOnClickListener {
            showRegisterDialog()
        }
    }

    private fun deleteUser(position: Int) {
        if (position in 0 until userList.size) {
            val userToDelete = userList[position]

            val userController = UserController(UserDao)
            userController.deleteUser(userToDelete)

            userList.removeAt(position)
            userAdapter.notifyItemRemoved(position)
        }
    }

    private fun showEditDialog(user: User) {
        val editDialog = EditDialogFragment(user) { editedUser ->
            val userController = UserController(UserDao)
            userController.editUser(editedUser)

            val position = userList.indexOfFirst { it.id == editedUser.id }
            if (position != -1) {
                userList[position] = editedUser
                userAdapter.notifyItemChanged(position)
            }
        }
        editDialog.show(supportFragmentManager, "EditDialog")
    }

    private fun showRegisterDialog() {
        val dialog = RegisterDialogFragment { username, password ->
            if (UserRepository.register(username, password)) {
                // Actualiza la lista y notifica
                userList.clear()
                userList.addAll(UserRepository.getUserController().getAllUsers())
                userAdapter.notifyDataSetChanged()

                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show(supportFragmentManager, "RegisterDialog")
    }

}
