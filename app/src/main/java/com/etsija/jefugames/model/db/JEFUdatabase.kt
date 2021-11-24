package com.etsija.jefugames.model.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

@Database(
    entities = [Team::class, Game::class, GameEvent::class],
    version = 10)
abstract class JEFUdatabase: RoomDatabase() {

    abstract fun jefuDao(): JEFUDao

    companion object {
        @Volatile
        private var INSTANCE: JEFUdatabase? = null

        fun getInstance(context: Context): JEFUdatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        JEFUdatabase::class.java,
                        "jefu_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}