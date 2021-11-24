package com.etsija.jefugames.view.epoxy

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyController
import com.etsija.jefugames.R
import com.etsija.jefugames.databinding.ModelEmptyStateBinding
import com.etsija.jefugames.databinding.ModelHeaderTeamBinding
import com.etsija.jefugames.databinding.ModelTeamBinding
import com.etsija.jefugames.model.db.Team

class TeamEpoxyController(
    private val teamInterface: TeamInterface
): EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var teams = ArrayList<Team>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    override fun buildModels() {

        if (isLoading) {
            LoadingEpoxyModel().id("loading_state").addTo(this)
            return
        }

        if (teams.isEmpty()) {
            EmptyStateEpoxyModel().id("empty_state").addTo(this)
            return
        }

        // Sort teams and insert headers
        var currentSelectedForGame: Int = -1
        teams.sortedByDescending {
            it.selectedForGame
        }.forEach { team ->
            if (team.selectedForGame != currentSelectedForGame) {
                currentSelectedForGame = team.selectedForGame
                val text = getHeaderForTeams(currentSelectedForGame)
                HeaderEpoxyModel(text).id(text).addTo(this)
            }
            TeamEpoxyModel(team, teamInterface).id(team.id).addTo(this)
        }
    }

    private fun getHeaderForTeams(x: Int): String {
        return when (x) {
            1 -> "Vierasjoukkue (vain yksi sallittu)"
            2 -> "Kotijoukkue (vain yksi sallittu)"
            else -> "Joukkueet"
        }
    }

    // This is an Epoxy model for one team in the list
    data class TeamEpoxyModel(
        val team: Team,
        val teamInterface: TeamInterface
    ): ViewBindingKotlinModel<ModelTeamBinding>(R.layout.model_team) {

        override fun ModelTeamBinding.bind() {
            tvName.text = team.name

            if (team.hometown == null) {
                tvHometown.isGone = true
            } else {
                tvHometown.isVisible = true
                tvHometown.text = team.hometown
            }

            if (team.manager == null) {
                tvManager.isGone = true
            } else {
                tvManager.isVisible = true
                tvManager.text = team.manager
            }

            // Trashcan symbol listener -> delete team
            ivDelete.setOnClickListener {

                teamInterface.onDeleteTeam(team)
            }

            // Listener for selecting the team for game, either as Hometeam or Awayteam
            tvSelectedForGame.setOnClickListener {
                teamInterface.onTeamSelectedForGame(team)
            }

            val text = when (team.selectedForGame) {
                1 -> "V"
                2 -> "K"
                else -> ""
            }
            tvSelectedForGame.text = text

            val color = when (team.selectedForGame) {
                1 -> Color.BLUE
                2 -> Color.RED
                else -> Color.GRAY
            }
            tvSelectedForGame.setBackgroundColor(color)
            root.setStrokeColor(ColorStateList.valueOf(color))
        }
    }

    // Handle empty state
    class EmptyStateEpoxyModel:
        ViewBindingKotlinModel<ModelEmptyStateBinding>(R.layout.model_empty_state) {
        override fun ModelEmptyStateBinding.bind() {
            // nothing to do
        }
    }

    // Header for Hometeam and Awayteam
    data class HeaderEpoxyModel(
        val headerText: String
    ): ViewBindingKotlinModel<ModelHeaderTeamBinding>(R.layout.model_header_team) {
        override fun ModelHeaderTeamBinding.bind() {
            tvHeaderTeam.text = headerText
        }
    }

}