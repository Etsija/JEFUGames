package com.etsija.jefugames.model.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GameDao {

    // CRUD

    @Insert
    abstract suspend fun insert(game: Game)

    suspend fun insertWithTimestamp(game: Game) {
        insert(game.apply {
            createdAt = System.currentTimeMillis()
            modifiedAt = System.currentTimeMillis()
        })
    }

    @Update
    abstract suspend fun update(game: Game)

    @Delete
    abstract suspend fun delete(game: Game)

    @Query("SELECT * FROM tbl_game ORDER BY date_start DESC")
    abstract fun getGames(): Flow<List<Game>>

    // Queries needing relations


}