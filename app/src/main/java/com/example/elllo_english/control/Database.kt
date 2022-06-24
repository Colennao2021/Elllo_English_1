package com.example.elllo_english.control


import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elllo_english.models.Course
import com.example.elllo_english.models.Grammar
import com.example.elllo_english.models.Level
import com.example.elllo_english.models.Script
import com.example.elllo_english.utils.AppData

@androidx.room.Database(
    entities = [Course::class, Grammar::class, Level::class, Script::class],
    version = 1,
    exportSchema = true
)
abstract class Database : RoomDatabase() {

    abstract fun dao(): DAO

    companion object {
        @Volatile
        private var instance: Database? = null

        fun getInstance(context: Context): Database {
            if (instance != null) {
                return instance as Database
            }
            synchronized(this) {
                instance = Room.databaseBuilder(
                    context,
                    Database::class.java,
                    AppData.DATABASE_NAME
                ).createFromAsset(AppData.DB_PATH)
                    .build()
                return instance as Database
            }
        }
    }
}