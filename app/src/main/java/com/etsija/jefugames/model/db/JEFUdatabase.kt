package com.etsija.jefugames.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Team::class, Game::class, GameEvent::class, GameEventType::class],
    version = 13)
abstract class JEFUdatabase: RoomDatabase() {

    abstract fun teamDao(): TeamDao
    abstract fun gameDao(): GameDao
    abstract fun gameEventDao(): GameEventDao
    abstract fun gameEventTypeDao(): GameEventTypeDao

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