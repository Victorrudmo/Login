package com.example.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.models.User

class UserAdapter(private val userList: List<User>, private val onDeleteClick: (Int) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View, onDeleteClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.findViewById<ImageView>(R.id.btn_delete).setOnClickListener {
                onDeleteClick(adapterPosition)
            }
        }

        fun renderize(user: User) {
            itemView.findViewById<TextView>(R.id.txtview_name).text = user.name
            itemView.findViewById<TextView>(R.id.txtview_contrasenna).text = user.contrasenna
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Inflar la vista del elemento de usuario con el nuevo diseño
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_user_list, parent, false)
        return UserViewHolder(view, onDeleteClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        // Llamar al método renderize en el ViewHolder para cargar los datos del usuario
        holder.renderize(userList[position])
    }

    override fun getItemCount(): Int {
        // Devolver el número de elementos en la lista
        return userList.size
    }
}


