package com.salman.roomtask.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 7/18/2023.
 * TODO (Bonus) Separate entities from domain models (use mappers)
 */
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val text: String
)
