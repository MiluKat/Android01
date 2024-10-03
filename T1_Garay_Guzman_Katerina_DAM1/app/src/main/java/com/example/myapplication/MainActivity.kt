package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var costoServicio: Double = 0.0
    var costoInstalacion: Double = 0.0
    var porcentajeDescuento: Double = 0.0
    var descuento: Double = 0.0
    var totalPagar: Double = 0.0
    var servicioSeleccionado: String = ""

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCalcular.setOnClickListener{

            if(binding.rbDuo.isChecked){
                servicioSeleccionado = "Duo Telefono Internet"
                costoServicio = 109.0
                costoInstalacion=35.0
                porcentajeDescuento = 5.0
            }

            if(binding.rbTrio.isChecked){
                servicioSeleccionado = "Trio Telefono TV Internet"
                costoServicio = 159.0
                costoInstalacion = 60.0
                porcentajeDescuento = 10.0
            }

            if(binding.rbInternet.isChecked){
                servicioSeleccionado = "Solo Internet"
                costoServicio = 89.0
                costoInstalacion = 15.0
                porcentajeDescuento = 2.0
            }

            if(binding.rbTelefono.isChecked){
                servicioSeleccionado = "Solo Telefono"
                costoServicio = 59.0
                costoInstalacion = 12.0
                porcentajeDescuento = 1.0
            }

            if(binding.rbCable.isChecked){
                servicioSeleccionado = "Solo Cable"
                costoServicio = 79.0
                costoInstalacion = 15.0
                porcentajeDescuento = 1.0
            }

            descuento = costoServicio * porcentajeDescuento / 100
            totalPagar = costoServicio - descuento + costoInstalacion

            binding.etCostoServicio.setText(costoServicio.toString())
            binding.etCostoInstalacion.setText(costoInstalacion.toString())
            binding.etDescuento.setText(descuento.toString())
            binding.etTotalPagar.setText(totalPagar.toString())
        }

        binding.btnImprimir.setOnClickListener {

            val newView = Intent(this, Destino::class.java)
            newView.putExtra("nombreCliente","${binding.etNombreCliente.text}")
            newView.putExtra("dni","${binding.etDni.text}")
            newView.putExtra("servicio","$servicioSeleccionado")
            newView.putExtra("costoServicio","$costoServicio")
            newView.putExtra("costoInstalacion","$costoInstalacion")
            newView.putExtra("descuento","$descuento")
            newView.putExtra("totalPagar","$totalPagar")
            startActivity(newView)
        }
    }
}