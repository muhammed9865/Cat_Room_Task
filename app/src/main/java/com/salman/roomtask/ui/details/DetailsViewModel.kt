package com.salman.roomtask.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salman.roomtask.database.AppDatabase
import com.salman.roomtask.model.Category
import com.salman.roomtask.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val database by lazy {
        AppDatabase.getInstance()
    }

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    fun loadCategory(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            database.dao.getCategoryWithNotesById(id).let {
                _notes.postValue(it!!.notes)
            }
        }
    }

}