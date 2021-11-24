package com.etsija.jefugames.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class JEFUDao {

    // Team CRUD

    @Insert
    abstract fun insert(team: Team)

    fun insertWithTimestamp(team: Team) {
        insert(team.apply {
            createdAt = System.currentTimeMillis()
            modifiedAt = System.currentTimeMillis()
        })
    }

    @Query("SELECT * FROM tbl_team ORDER BY name ASC")
    abstract fun getTeams(): Flow<List<Team>>

    @Update
    abstract suspend fun update(team: Team)

    @Delete
    abstract suspend fun delete(team: Team)

    // Game CRUD

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(game: Game)

    @Query("SELECT * FROM tbl_game ORDER BY date_start DESC")
    abstract fun getGames(): Flow<List<Game>>

    @Update
    abstract suspend fun update(game: Game)

    @Delete
    abstract suspend fun delete(game: Game)

    // Event CRUD

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(gameEvent: GameEvent)

    @Query("SELECT * FROM tbl_game_event ORDER BY gametime DESC")
    abstract fun getEvents(): Flow<List<GameEvent>>

    @Update
    abstract suspend fun update(gameEvent: GameEvent)

    @Delete
    abstract suspend fun delete(gameEvent: GameEvent)

    // Queries needing relations


}