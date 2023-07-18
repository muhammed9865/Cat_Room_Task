package com.salman.roomtask.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salman.roomtask.database.AppDatabase
import com.salman.roomtask.model.Category
import com.salman.roomtask.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 7/18/2023.
 * TODO (Bonus) Extract the database logic into a repository
 * TODO (Bonus) Use Hilt to inject the repository into the ViewModel
 * TODO (Bonus) Use StateFlow instead of Livedata
 */
class HomeViewModel : ViewModel() {
    private val database by lazy {
        AppDatabase.getInstance()
    }

    init {
        loadCategories()
        loadNotes()
    }

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    private fun loadCategories() = viewModelScope.launch(Dispatchers.IO) {
        database.dao.getAllCategories().let {
            _categories.postValue(it)
        }
    }

    private fun loadNotes() = viewModelScope.launch(Dispatchers.IO) {
        database.dao.getAllNotes().let {
            _notes.postValue(it)
        }
    }
}