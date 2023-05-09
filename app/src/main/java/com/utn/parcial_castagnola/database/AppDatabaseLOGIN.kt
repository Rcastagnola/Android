package com.utn.parcial_castagnola.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.utn.parcial_castagnola.entities.Register

@Database(entities = [Register::class], version = 1, exportSchema = false)
abstract class AppDatabaseLOGIN : RoomDatabase(){

    abstract fun userDao() : UserDao

    companion object {
        private var INSTANCE: AppDatabaseLOGIN? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabaseLOGIN? {
            if(INSTANCE == null){
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE
        }

        private fun buildDatabase(context: Context): AppDatabaseLOGIN?{
            if (INSTANCE == null){
                synchronized(AppDatabaseLOGIN::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,AppDatabaseLOGIN::class.java, "LogDB"
                    )

                        .addCallback(StartingUsers(context))
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}