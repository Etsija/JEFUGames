package com.etsija.jefugames.model

import androidx.lifecycle.LiveData
import com.etsija.jefugames.model.db.JEFUDao
import com.etsija.jefugames.model.db.JEFUdatabase
import com.etsija.jefugames.model.db.Team
import kotlinx.coroutines.flow.Flow

class JEFURepository(
    private val db: JEFUdatabase
) {
    suspend fun insertTeam(team: Team) {
        db.jefuDao().insertWithTimestamp(team)
    }

    suspend fun updateTeam(team: Team) {
        db.jefuDao().update(team)
    }

    suspend fun deleteTeam(team: Team) {
        db.jefuDao().delete(team)
    }

    fun getTeams(): Flow<List<Team>> {
        return db.jefuDao().getTeams()
    }
}