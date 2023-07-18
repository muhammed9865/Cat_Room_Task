package com.salman.roomtask.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salman.roomtask.model.Category
import com.salman.roomtask.model.Note

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 7/18/2023.
 * TODO (Bonus) Find a way to segregate the DAOs into different files
 */
@Dao
interface AppDao {

    @Insert
    suspend fun insertCategory(category: Category)

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<Category>

    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>
}