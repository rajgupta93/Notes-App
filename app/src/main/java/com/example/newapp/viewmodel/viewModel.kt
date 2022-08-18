package com.example.newapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Database
import com.example.newapp.database.database
import com.example.newapp.model.notes
import com.example.newapp.repository.Repository

class viewModel(application: Application): AndroidViewModel(application) {

    lateinit var respository : Repository

    init {
        val dao = database.getDatabase(application).notesDao()
        respository = Repository(dao)
    }

    fun addNotes(Notes: notes) {
        respository.insertNotes(Notes)
    }

    fun getNotes(): LiveData<List<notes>> {
        return respository.getNotes()
    }


    fun updateNotes(Notes: notes) {
        return respository.update(Notes)
    }

    fun deleteNotes(id: Int) {
        return respository.delete(id)
    }

    fun getHighPriorityNotes(): LiveData<List<notes>> {
        return respository.getHighPriorityNotes()
    }

    fun getMediumPriorityNotes(): LiveData<List<notes>> {
        return respository.getMediumPriorityNotes()
    }

    fun getLowPriorityNotes(): LiveData<List<notes>> {
        return respository.getLowPriorityNotes()
    }



}