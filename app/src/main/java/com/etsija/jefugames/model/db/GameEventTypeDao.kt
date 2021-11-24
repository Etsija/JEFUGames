package com.etsija.jefugames.model.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GameEventTypeDao {

    // CRUD

    @Insert
    abstract suspend fun insert(gameEventType: GameEventType)

    suspend fun insertWithTimestamp(gameEventType: GameEventType) {
        insert(gameEventType.apply {
            createdAt = System.currentTimeMillis()
            modifiedAt = System.currentTimeMillis()
        })
    }

    @Update
    abstract suspend fun update(gameEventType: GameEventType)

    @Delete
    abstract suspend fun delete(gameEventType: GameEventType)

    @Query("SELECT * FROM tbl_game_event_type ORDER BY points ASC")
    abstract fun getGameEventTypes(): Flow<List<GameEventType>>

    // Queries needing relations


}