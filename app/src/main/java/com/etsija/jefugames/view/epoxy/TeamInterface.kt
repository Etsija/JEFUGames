package com.etsija.jefugames.view.epoxy

import com.etsija.jefugames.model.db.Team

interface TeamInterface {

    fun onDeleteTeam(team: Team)
    fun onTeamSelected(team: Team)
    fun onTeamSelectedForGame(team: Team)
}