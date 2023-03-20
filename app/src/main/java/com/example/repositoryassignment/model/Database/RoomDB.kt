package com.example.repositoryassignment.model.Database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.repositoryassignment.model.data.RoomData

@Database(entities = [RoomData::class],version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun ResponseDao():RoomDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(context: Context): RoomDB {
            val tempinstance = INSTANCE
            if (tempinstance != null) {
                return tempinstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDB::class.java,
                        "Roomdatabase4"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }

    }

}