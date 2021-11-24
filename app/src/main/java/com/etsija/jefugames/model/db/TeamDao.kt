package com.etsija.jefugames.model.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TeamDao {

    // CRUD

    @Insert
    abstract suspend fun insert(team: Team)

    suspend fun insertWithTimestamp(team: Team) {
        insert(team.apply {
            createdAt = System.currentTimeMillis()
            modifiedAt = System.currentTimeMillis()
        })
    }

    @Update
    abstract suspend fun update(team: Team)

    @Delete
    abstract suspend fun delete(team: Team)

    @Query("SELECT * FROM tbl_team ORDER BY name ASC")
    abstract fun getTeams(): Flow<List<Team>>

    // Queries needing relations


}