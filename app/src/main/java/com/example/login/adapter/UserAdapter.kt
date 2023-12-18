package com.example.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.dao.UserDao.userList
import com.example.login.models.User

class UserAdapter(
    private val userList: List<User>,
    private val onDeleteClick: (Int) -> Unit,
    private val onEditClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(
        itemView: View,
        onDeleteClick: (Int) -> Unit,
        onEditClick: (User) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.findViewById<ImageView>(R.id.btn_delete).setOnClickListener {
                onDeleteClick(adapterPosition)
            }
            itemView.findViewById<ImageView>(R.id.btn_edit).setOnClickListener {
                onEditClick(userList[adapterPosition])
            }
        }

        fun renderize(user: User) {
            itemView.findViewById<TextView>(R.id.txtview_name).text = user.name
            itemView.findViewById<TextView>(R.id.txtview_contrasenna).text = user.contrasenna
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_user_list, parent, false)
        return UserViewHolder(view, onDeleteClick, onEditClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.renderize(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}





