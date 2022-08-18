package com.example.newapp.repository

import androidx.lifecycle.LiveData
import com.example.newapp.dao.NotesDao
import com.example.newapp.model.notes

class Repository(val notesDao: NotesDao) {

    fun getNotes(): LiveData<List<notes>>  {
        return notesDao.getNotes()
    }

    fun getHighPriorityNotes(): LiveData<List<notes>>  {
        return notesDao.getHighPriorityNotes()
    }

    fun getMediumPriorityNotes(): LiveData<List<notes>>  {
        return notesDao.getMediumPriorityNotes()
    }

    fun getLowPriorityNotes(): LiveData<List<notes>>  {
        return notesDao.getLowPriorityNotes()
    }
    fun insertNotes(note: notes) {
        notesDao.insert(note)
    }

    fun delete(id: Int) {
        notesDao.delete(id)
    }

    fun update(note: notes) {
        notesDao.update(note)
    }
}