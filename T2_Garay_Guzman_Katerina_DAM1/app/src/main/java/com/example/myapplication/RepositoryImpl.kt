package com.example.myapplication

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.example.myapplication.model.Personaje


class RepositoryImpl: Repository {
    override suspend fun getPesonajes(): List<Personaje> {
        try {
            val response = Factory.client.get("https://hp-api.onrender.com/api/characters")
            return response.body<List<Personaje>>().toList()
        }catch (e: Exception){
            Log.e("Error RepositoryImpl","El error es: ${e.message}")
            return listOf()
        }
    }
}