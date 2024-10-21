package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var flagEsVisible = false
    val service: RepositoryImpl = RepositoryImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rv.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            val lstPersonajes = service.getPesonajes()
            for(data in lstPersonajes){
                Log.w("Response service","data Nombre: ${data.name} - Imagen ${data.image} - Actor: ${data.actor}")
            }
            withContext(Dispatchers.Main){
                binding.rv.adapter = PersonajeAdapter(lstPersonajes)
            }
        }

        binding.rv.visibility = View.GONE

        binding.btnMostrar.setOnClickListener {
            if(flagEsVisible) {
                binding.rv.visibility = View.GONE

                binding.btnMostrar.text = "Mostrar"
                flagEsVisible = false
            }
            else {
                binding.rv.visibility = View.VISIBLE

                binding.btnMostrar.text = "Ocultar"
                flagEsVisible = true
            }
        }

    }
}