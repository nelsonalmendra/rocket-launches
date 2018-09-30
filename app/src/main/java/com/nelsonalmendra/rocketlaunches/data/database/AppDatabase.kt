package com.nelsonalmendra.rocketlaunches.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Rocket::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rocketDao(): RocketDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}