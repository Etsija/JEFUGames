package com.etsija.jefugames.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.etsija.jefugames.R
import com.etsija.jefugames.databinding.FragmentTeamBinding
import com.etsija.jefugames.databinding.FragmentTeamEditBinding
import com.etsija.jefugames.model.db.Team

class TeamEditFragment : BaseFragment() {

    private var _binding: FragmentTeamEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            saveTeamToDatabase()
        }

        sharedViewModel.transactionCompleteLiveData.observe(viewLifecycleOwner) { complete ->
            if (complete) {
                Toast.makeText(requireActivity(), "Joukkue tallennettu!", Toast.LENGTH_SHORT).show()
                binding.etName.text = null
                binding.etName.requestFocus()
                binding.etHometown.text = null
                binding.etManager.text = null
                mainActivity.showKeyboard()
            }
        }
        binding.etName.requestFocus()
        mainActivity.showKeyboard()
    }

    override fun onPause() {
        super.onPause()
        sharedViewModel.transactionCompleteLiveData.postValue(false)
    }

    // Save a new Team to the database
    private fun saveTeamToDatabase() {
        val name = binding.etName.text.toString().trim()
        if (name.isEmpty()) {
            binding.etName.error = "Required field"
            return
        }
        val hometown = binding.etHometown.text.toString().trim()
        val manager = binding.etManager.text.toString().trim()

        // Create the Team and send it to the sharedViewModel
        val team = Team(
            0,
            name = name,
            hometown = hometown,
            manager = manager,
            selectedForGame = 0,
            createdAt = 0,
            modifiedAt = 0
        )

        sharedViewModel.insertTeam(team)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}