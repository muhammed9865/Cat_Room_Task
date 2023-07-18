package com.salman.roomtask.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.salman.roomtask.MainApp
import com.salman.roomtask.database.dao.AppDao
import com.salman.roomtask.model.Category
import com.salman.roomtask.model.Note

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 7/18/2023.
 */
@Database(
    entities = [Category::class, Note::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: AppDao

    // TODO (Bonus) Extract this to DI module (use Hilt or Koin)
    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        MainApp.instance,
                        AppDatabase::class.java,
                        "app_database"
                    )
                        .build()
                }
            }
            return instance!!
        }
    }
}