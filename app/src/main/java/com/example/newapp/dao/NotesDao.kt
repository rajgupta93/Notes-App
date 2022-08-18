package com.example.newapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newapp.model.notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes")
    fun getNotes():LiveData<List<notes>>

    @Query("SELECT * FROM Notes WHERE priority = 1")
    fun getHighPriorityNotes():LiveData<List<notes>>

    @Query("SELECT * FROM Notes WHERE priority = 2")
    fun getMediumPriorityNotes():LiveData<List<notes>>

    @Query("SELECT * FROM Notes WHERE priority = 3")
    fun getLowPriorityNotes():LiveData<List<notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notes: notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun delete(id:Int)

    @Update
    fun update(note: notes)
}