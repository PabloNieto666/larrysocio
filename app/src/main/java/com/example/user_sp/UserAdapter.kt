package com.example.user_sp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.user_sp.databinding.ItemUserBinding

// clase que funciona como adaptador para el recycleview que recibe la lista y un objeto litener para manejar los eventos de click de la lista
class UserAdapter(private val users: List<User>, private val listener: Listener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    // variable para almacenar el contexto
    private lateinit var context: Context

    // crea una nueva vista para mostrar elementos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // obtiene el contexto del padre
        context = parent.context
        // infla el diseño del elemento de usuario
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        // crea una nueva vista
        return ViewHolder(binding)
    }

    // reemplaza el contenido de una vista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // obtiene el usuario en la posición dada
        val user = users[position]
        // actualiza los elementos de la vista con los datos del usuario
        with(holder) {
            setListener(user, position + 1)
            binding.tvOrder.text = (position + 1).toString()
            binding.tvName.text = user.getFullName()
            Glide.with(context)
                .load(user.ulr)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPhoto)
        }
    }

    // devuelve el número total de elementos en el conjunto de datos
    override fun getItemCount(): Int = users.size

    // representa una vista de usuario
    inner class ViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // establece un listener en la vista para escuchar eventos de clic
        fun setListener(user: User, position: Int) {
            binding.root.setOnClickListener { listener.onClick(user, position) }
        }
    }

    // define una interfaz para escuchar eventos de clic en la vista
    interface Listener {
        fun onClick(user: User, position: Int)
    }
}



