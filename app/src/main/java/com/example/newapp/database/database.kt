package com.example.newapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newapp.dao.NotesDao
import com.example.newapp.model.notes
@Database(entities = arrayOf(notes::class), version = 1, exportSchema = false)
abstract class database: RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: database? = null

        fun getDatabase(context: Context): database {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    database::class.java,
                    "notes_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}