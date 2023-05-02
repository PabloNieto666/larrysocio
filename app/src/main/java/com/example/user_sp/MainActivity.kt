package com.example.user_sp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.user_sp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), UserAdapter.Listener {

    // Declaración de variables
    private lateinit var userAdapter: UserAdapter // Adaptador para la lista de usuarios
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager // Administrador de diseño de la lista
    private lateinit var binding: ActivityMainBinding // Objeto de enlace a la vista

    override fun onCreate(savedInstanceState: Bundle?) { // es el método que se ejecuta cuando la actividad se está creando.
        super.onCreate(savedInstanceState) //En este caso, primero llama al método onCreate de la clase AppCompatActivity.

        // Inflar la vista de la actividad utilizando el enlace de vista generado por ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Crear una instancia del adaptador para la lista de usuarios y configurar el Listener
        userAdapter = UserAdapter(getUsers(), this)

        // Crear una instancia del administrador de diseño de lista
        linearLayoutManager = LinearLayoutManager(this)

        // Configurar el RecyclerView para usar el adaptador y el administrador de diseño
        binding.rvVistaUsu.apply {
            layoutManager = linearLayoutManager // Establecer el administrador de diseño de la lista
            adapter = userAdapter // Establecer el adaptador para la lista de usuarios
        }
    }

    // Función que devuelve una lista de usuarios para ser utilizada por el adaptador
    private fun getUsers(): MutableList<User>{
        val users = mutableListOf<User>()

        // Crear objetos User con datos ficticios
        val obj1 = User(1, "Pablo", "Nieto", "https://cdn.pixabay.com/photo/2016/07/30/21/37/mario-1558062_1280.jpg")
        val obj2 = User(2, "Larry", "Angel", "https://www.publicdomainpictures.net/pictures/10000/nahled/1-1248160875fSW9.jpg")
        val obj3 = User(3, "Pacho", "Squid", "https://cdn.pixabay.com/photo/2021/03/04/09/10/anime-6067344_1280.jpg")
        val obj4 = User(4, "Tiger", "Ish", "https://cdn.pixabay.com/photo/2021/03/02/17/26/pixel-6063246_1280.png")

        // Agregar los objetos User a la lista de usuarios
        users.add(obj1)
        users.add(obj2)
        users.add(obj3)
        users.add(obj4)

        // Devolver la lista de usuarios
        return users
    }

    // Función que maneja el evento de clic en un elemento de la lista
    override fun onClick(user: User, position: Int) {
        Toast.makeText(this,"$position: ${user.getFullName()}", Toast.LENGTH_SHORT).show()
    }
}

