package com.example.focuswolf.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.focuswolf.data.dao.ProjectDao
import com.example.focuswolf.data.dao.TaskDao
import com.example.focuswolf.data.entity.ProjectEntity
import com.example.focuswolf.data.entity.TaskEntity

@Database(
    entities = [ProjectEntity::class, TaskEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDao
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "focuswolf_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}