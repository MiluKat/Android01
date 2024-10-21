package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val correo: String

)