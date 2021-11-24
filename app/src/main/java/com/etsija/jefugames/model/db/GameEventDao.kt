package com.etsija.jefugames.model.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GameEventDao {

    // CRUD

    @Insert
    abstract suspend fun insert(gameEvent: GameEvent)

    suspend fun insertWithTimestamp(gameEvent: GameEvent) {
        insert(gameEvent.apply {
            createdAt = System.currentTimeMillis()
            modifiedAt = System.currentTimeMillis()
        })
    }

    @Update
    abstract suspend fun update(gameEvent: GameEvent)

    @Delete
    abstract suspend fun delete(gameEvent: GameEvent)

    @Query("SELECT * FROM tbl_game_event ORDER BY gametime ASC")
    abstract fun getGameEvents(): Flow<List<GameEvent>>

    // Queries needing relations


}