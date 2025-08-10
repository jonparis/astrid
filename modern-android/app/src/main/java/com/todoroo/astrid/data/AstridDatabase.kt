package com.todoroo.astrid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class AstridDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile private var INSTANCE: AstridDatabase? = null

        fun get(context: Context): AstridDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AstridDatabase::class.java,
                "database"
            ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
        }
    }
} 