package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityUserRoomBinding
import com.example.myapplication.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRoom : AppCompatActivity() {

    private lateinit var database: UserDatabase
    private lateinit var userDao: UserDAO
    private var userData: User? = null
    private  lateinit var binding: ActivityUserRoomBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = UserDatabase.getDatabase(this)
        userDao = database.userDao()
        binding.rv.layoutManager = LinearLayoutManager(this)

        adapter = UserAdapter(emptyList(),{deleteUser(it)})

        binding.rv.adapter = adapter

        binding.btnAdd.setOnClickListener {
            if(binding.etNombre.text.toString().trim() == ""
                || binding.etApellido.text.toString().trim() == "" )
            {
                Toast.makeText(this, "Ingrese los datos del Alumno", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val userTest = User(
                    name = binding.etNombre.text.toString(),
                    apellido = binding.etApellido.text.toString(),
                    anio = 2024)
                createUser(userTest)
            }
        }

        binding.btnLogout.setOnClickListener {
            UtilsSharedPreferences.cerrarSesion(this)
            startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }

        updateDate()
    }

    fun createUser(user: User){
        CoroutineScope(Dispatchers.IO).launch {
            userDao.insert(user)
            updateDate()
        }
    }

    fun updateUser(user: User?){
        CoroutineScope(Dispatchers.IO).launch {
            user?.let{
                userDao.update(it)
            }
            updateDate()
            userData = null
        }
    }

    fun updateDate(){
        CoroutineScope(Dispatchers.IO).launch {
            val users = userDao.getAllUser()
            withContext(Dispatchers.Main){
                adapter.updateUsers(users)
                
                //Limpiar camos
                binding.etNombre.setText("")
                binding.etApellido.setText("")
            }
        }
    }

    fun deleteUser(user: User){
        CoroutineScope(Dispatchers.IO).launch {
            userDao.delete(user)
            updateDate()
        }
    }
}