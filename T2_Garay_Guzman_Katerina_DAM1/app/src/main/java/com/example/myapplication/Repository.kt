package com.example.myapplication

import com.example.myapplication.model.Personaje

interface Repository {

    suspend fun getPesonajes(): List<Personaje>
}