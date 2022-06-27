package com.example.elllo.control


import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elllo.models.Course
import com.example.elllo.models.Grammar
import com.example.elllo.models.Level
import com.example.elllo.models.Script
import com.example.elllo.utils.AppData

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