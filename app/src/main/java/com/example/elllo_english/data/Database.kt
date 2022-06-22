package com.example.elllo_english.data


import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elllo_english.data.models.Course
import com.example.elllo_english.data.models.Grammar
import com.example.elllo_english.data.models.Level
import com.example.elllo_english.data.models.Script
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
                    .allowMainThreadQueries().build()
                return instance as Database
            }
        }
    }

}