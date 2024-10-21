package com.example.myapplication

import android.content.Context
import androidx.room.Database
import com.example.myapplication.model.User

@Database(entities = [User::class],version = 1, exportSchema = false)
abstract class UserDatabase {
    companion object {
        @Volatile
        private var INSTANCE : UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            return INSTANCE ?: Synchronized(this){
                val instance = Room.datbaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "userDatabase"
                )
            }
        }
    }
}