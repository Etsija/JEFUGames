package com.etsija.jefugames.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etsija.jefugames.model.JEFURepository
import com.etsija.jefugames.model.db.JEFUdatabase
import com.etsija.jefugames.model.db.Team
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private lateinit var repository: JEFURepository
    val teamsLiveData = MutableLiveData<List<Team>>()
    val transactionCompleteLiveData = MutableLiveData<Boolean>()

    // This absolutely has to be first in this class, since we don't use
    // dependency injection (DI) in this project!!!
    fun init(db: JEFUdatabase) {
        // Get the repository
        repository = JEFURepository(db)
        getTeams()
    }

    fun getTeams() = viewModelScope.launch {
        repository.getTeams().collect { teams ->
            teamsLiveData.postValue(teams)
        }
    }

    fun insertTeam(team: Team) = viewModelScope.launch {
        repository.insertTeam(team)
        transactionCompleteLiveData.postValue(true)
    }

    fun updateTeam(team: Team) = viewModelScope.launch {
        repository.updateTeam(team)
    }

    fun deleteTeam(team: Team) = viewModelScope.launch {
        repository.deleteTeam(team)
    }
}