package com.salman.roomtask.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.salman.roomtask.MainApp
import com.salman.roomtask.database.dao.AppDao
import com.salman.roomtask.model.Category
import com.salman.roomtask.model.Note

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 7/18/2023.
 */
@Database(
    entities = [Category::class, Note::class],
    version = 3,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 1, to = 2)]
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
                        .addMigrations(migration2To3)
                        .build()
                }
            }
            return instance!!
        }

        private val migration2To3 = object : Migration(2, 3){
            override fun migrate(database: SupportSQLiteDatabase) {
                val updateFirst20 = "UPDATE notes SET categoryId = 1 WHERE id <= 21"
                database.execSQL(updateFirst20)

                val updateSecond20 = "UPDATE notes SET categoryId = 2 WHERE id > 21 AND id <= 41"
                database.execSQL(updateSecond20)

                val updateThird20 = "UPDATE notes SET categoryId = 3 WHERE id > 41 AND id <= 61"
                database.execSQL(updateThird20)

                val updateFourth20 = "UPDATE notes SET categoryId = 4 WHERE id > 61 AND id <= 81"
                database.execSQL(updateFourth20)
            }

        }
    }
}