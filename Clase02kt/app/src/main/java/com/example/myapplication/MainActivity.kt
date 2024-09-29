package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var num1: Int = 10
    var num2: Int = 20
    var resultado: Int = 0
    var aux: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultado = num1 + num2
        Log.v("Resultado", resultado.toString())
        Log.d("Resultado", resultado.toString())
        Log.i("Resultado", resultado.toString())
        Log.w("Resultado", "$resultado")
        Log.e("Resultado", "$resultado")
        Log.wtf("Resultado", "$resultado")


    }

    fun crearLog(): Unit {
        Log.e( "Error", "Error desde función")
    }


    fun sumar(a: Int, b: Int): Int {
        return a + b
    }




}