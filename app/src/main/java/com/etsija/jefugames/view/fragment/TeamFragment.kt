package com.etsija.jefugames.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.etsija.jefugames.R
import com.etsija.jefugames.databinding.FragmentTeamBinding
import com.etsija.jefugames.model.db.Team
import com.etsija.jefugames.view.epoxy.TeamEpoxyController
import com.etsija.jefugames.view.epoxy.TeamInterface
import com.etsija.jefugames.viewmodel.SharedViewModel

class TeamFragment : BaseFragment(), TeamInterface {

    private var _binding: FragmentTeamBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate from Team Fragment to team add/change Fragment
        binding.fab.setOnClickListener {
            navigateViaNavGraph(R.id.action_teamFragment_to_teamEditFragment)
        }

        val controller = TeamEpoxyController(this)
        binding.epoxyRecyclerView.setController(controller)

        sharedViewModel.teamsLiveData.observe(viewLifecycleOwner) { teams ->
            controller.teams = teams as ArrayList<Team>

        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.hideKeyboard(requireView())
    }

    // Implement TeamInterface functions

    override fun onDeleteTeam(team: Team) {
        sharedViewModel.deleteTeam(team)
    }

    override fun onTeamSelected(team: Team) {
        // todo
    }

    override fun onTeamSelectedForGame(team: Team) {
        val currentSel = team.selectedForGame
        var newSel = currentSel + 1
        if (newSel > 2) {
            newSel = 0
        }
        Log.d("After bumping: ", newSel.toString())
        val updatedTeam = team.copy(selectedForGame = newSel)
        Log.d("New team: ", updatedTeam.toString())
        sharedViewModel.updateTeam(updatedTeam)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}