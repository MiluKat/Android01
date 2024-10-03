package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityDestinoBinding

class Destino : AppCompatActivity() {

    private lateinit var binding: ActivityDestinoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombreCliente = intent.getStringExtra("nombreCliente")?: "no hay data"
        val dni = intent.getStringExtra("dni")?: "no hay data"
        val servicio = intent.getStringExtra("servicio")?: "no hay data"
        val costoServicio = intent.getStringExtra("costoServicio")?: "no hay data"
        val costoInstalacion = intent.getStringExtra("costoInstalacion")?: "no hay data"
        val descuento = intent.getStringExtra("descuento")?: "no hay data"
        val totalPagar = intent.getStringExtra("totalPagar")?: "no hay data"


        binding.txtResultado.text = " Resumen de servicio \n " +
                                    " Nombres del cliente: $nombreCliente \n " +
                                    " Dni: $dni \n " +
                                    " Servicio: $servicio \n " +
                                    " Costo de Servicio: $costoServicio \n" +
                                    " Costo de Instalacion: $costoInstalacion \n" +
                                    " Descuento: $descuento \n" +
                                    " Total a Pagar: $totalPagar"
    }
}