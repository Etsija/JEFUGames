package com.etsija.jefugames.model

import com.etsija.jefugames.model.db.Game
import com.etsija.jefugames.model.db.JEFUdatabase
import com.etsija.jefugames.model.db.Team
import kotlinx.coroutines.flow.Flow

class JEFURepository(
    private val db: JEFUdatabase
) {
    // region Team

    suspend fun insertTeam(team: Team) {
        db.teamDao().insertWithTimestamp(team)
    }

    suspend fun updateTeam(team: Team) {
        db.teamDao().update(team)
    }

    suspend fun deleteTeam(team: Team) {
        db.teamDao().delete(team)
    }

    fun getTeams(): Flow<List<Team>> {
        return db.teamDao().getTeams()
    }

    // endregion Team

    // region Game

    suspend fun insertGame(game: Game) {
        db.gameDao().insertWithTimestamp(game)
    }

    suspend fun updateGame(game: Game) {
        db.gameDao().update(game)
    }

    suspend fun deleteGame(game: Game) {
        db.gameDao().delete(game)
    }

    fun getGames(): Flow<List<Game>> {
        return db.gameDao().getGames()
    }

    // endregion Game


}