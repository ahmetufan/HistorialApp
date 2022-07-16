package com.ahmet.HistoryApp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmet.HistoryApp.model.*


@Database(entities = [User::class,Bili::class,Lieder::class,Filo::class,Savvas::class], version = 8)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null


        private val lock = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(lock) {

            INSTANCE ?: makeDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, UserDatabase::class.java, "user_database"
        ).fallbackToDestructiveMigration().build()
    }
}