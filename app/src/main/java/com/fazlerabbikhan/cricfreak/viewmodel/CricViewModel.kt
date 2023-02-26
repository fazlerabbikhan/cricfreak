package com.fazlerabbikhan.cricfreak.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fazlerabbikhan.cricfreak.database.CricDatabase
import com.fazlerabbikhan.cricfreak.repository.CricRepository
import com.fazlerabbikhan.cricfreak.global.Constant
import com.fazlerabbikhan.cricfreak.model.fixtures.FixtureData
import com.fazlerabbikhan.cricfreak.model.leagues.LeagueData
import com.fazlerabbikhan.cricfreak.model.livescores.LivescoreData
import com.fazlerabbikhan.cricfreak.model.players.PlayersData
import com.fazlerabbikhan.cricfreak.model.rankings.RankingData
import com.fazlerabbikhan.cricfreak.model.teams.TeamData
import com.fazlerabbikhan.cricfreak.network.CricApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CricViewModel(application: Application): AndroidViewModel(application){

    private val _leagues = MutableLiveData<List<LeagueData>>()
    private val leagues: LiveData<List<LeagueData>> = _leagues

    private val _teams = MutableLiveData<List<TeamData>>()
    val teams: LiveData<List<TeamData>> = _teams

    private val _players = MutableLiveData<List<PlayersData>?>()
    val players: LiveData<List<PlayersData>?> = _players

    private val _upcoming = MutableLiveData<List<FixtureData>?>()
    val upcoming: LiveData<List<FixtureData>?> = _upcoming

    private val _recentInternational = MutableLiveData<List<FixtureData>?>()
    val recentInternational: LiveData<List<FixtureData>?> = _recentInternational

    private val _recentDomestic = MutableLiveData<List<FixtureData>?>()
    val recentDomestic: LiveData<List<FixtureData>?> = _recentDomestic

    private val _testrankings = MutableLiveData<List<RankingData>>()
    val testrankings: LiveData<List<RankingData>> = _testrankings

    private val _odirankings = MutableLiveData<List<RankingData>>()
    val odirankings: LiveData<List<RankingData>> = _odirankings

    private val _t20irankings = MutableLiveData<List<RankingData>>()
    val t20irankings: LiveData<List<RankingData>> = _t20irankings

    private val _livescores = MutableLiveData<List<LivescoreData>>()
    val livescores: LiveData<List<LivescoreData>> = _livescores

    private val repository: CricRepository

    private val readLeagueData: LiveData<List<LeagueData>>
    private val readTeamData: LiveData<List<TeamData>>

    init{
        val cricDao = CricDatabase.getDatabase(application).getCricDao()
        repository = CricRepository(cricDao)
        readLeagueData = repository.readLeagueData
        readTeamData = repository.readTeamData
    }

    fun catchLeagues(): LiveData<List<LeagueData>>{
        viewModelScope.launch {
            try {
                _leagues.value = CricApi.retrofitService.fetchLeagues(Constant.TOKEN).data
                Log.d("Api", "Leagues: ${leagues.value?.size}")
            }
            catch (e: java.lang.Exception) {
                _leagues.value = listOf()
                Log.d("Exception","$e")
            }
        }
        return leagues
    }

    fun addLeagueList(leagues: List<LeagueData>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLeague(leagues)
        }
    }

    @SuppressLint("LongLogTag")
    fun catchTeams(): LiveData<List<TeamData>>{
        viewModelScope.launch {
            try {
                _teams.value = CricApi.retrofitService.fetchTeams(Constant.TOKEN).data
                Log.d("Api", "Teams: ${teams.value?.size}")
            }
            catch (e: java.lang.Exception) {
                _teams.value = listOf()
                Log.d("Exception","$e")
            }
        }
        return teams
    }

    fun addTeamList(teams: List<TeamData>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTeam(teams)
        }
    }

    @SuppressLint("LongLogTag")
    fun catchPlayers(){
        viewModelScope.launch {
            try {
                _players.value = CricApi.retrofitService.fetchPlayers(Constant.TOKEN).data
                Log.d("Api", "Upcoming: ${players.value?.size}")
            }
            catch (e: java.lang.Exception) {
                _players.value = listOf()
                Log.d("Exception","$e")
            }
        }
    }

    @SuppressLint("LongLogTag")
    fun catchUpcoming(){
        viewModelScope.launch {
            try {
                _upcoming.value = CricApi.retrofitService.fetchUpcoming(Constant.TOKEN).data
                Log.d("Api", "Upcoming: ${upcoming.value?.size}")
            }
            catch (e: java.lang.Exception) {
                _upcoming.value = listOf()
                Log.d("Exception","$e")
            }
        }
    }

    @SuppressLint("LongLogTag")
    fun catchRecentInternational(){
        viewModelScope.launch {
            try {
                _recentInternational.value = CricApi.retrofitService.fetchRecentInternational(Constant.TOKEN).data
                Log.d("Api", "Recent International: ${recentInternational.value?.size}")
            }
            catch (e: java.lang.Exception) {
                _recentInternational.value = listOf()
                Log.d("Exception","$e")
            }
        }
    }

    @SuppressLint("LongLogTag")
    fun catchRecentDomestic(){
        viewModelScope.launch {
            try {
                _recentDomestic.value = CricApi.retrofitService.fetchRecentDomestic(Constant.TOKEN).data
                Log.d("Api", "Recent Domestic: ${recentDomestic.value?.size}")
            }
            catch (e: java.lang.Exception) {
                _recentDomestic.value = listOf()
                Log.d("Exception","$e")
            }
        }
    }

    @SuppressLint("LongLogTag")
    fun catchTestRankings(){
        viewModelScope.launch {
            try {
                _testrankings.value = CricApi.retrofitService.fetchTestRankings(Constant.TOKEN).data?.filter { it.gender=="men" }
                Log.d("Api", "Test Rankings: ${testrankings.value?.size}")
            }
            catch (e: java.lang.Exception) {
                _testrankings.value = listOf()
                Log.d("Exception","$e")
            }
        }
    }

    @SuppressLint("LongLogTag")
    fun catchODIRankings(){
        viewModelScope.launch {
            try {
                _odirankings.value = CricApi.retrofitService.fetchODIRankings(Constant.TOKEN).data?.filter { it.gender=="men" }
                Log.d("Api", "ODI Rankings: ${odirankings.value?.size}")
            }
            catch (e: java.lang.Exception) {
                _odirankings.value = listOf()
                Log.d("Exception","$e")
            }
        }
    }

    @SuppressLint("LongLogTag")
    fun catchT20IRankings(){
        viewModelScope.launch {
            try {
                _t20irankings.value = CricApi.retrofitService.fetchT20IRankings(Constant.TOKEN).data?.filter { it.gender=="men" }
                Log.d("Api", "T20I Rankings: ${t20irankings.value?.size}")
            }
            catch (e: java.lang.Exception) {
                _t20irankings.value = listOf()
                Log.d("Exception","$e")
            }
        }
    }

    @SuppressLint("LongLogTag")
    fun catchLivescores(){
        viewModelScope.launch {
            try {
                _livescores.value = CricApi.retrofitService.fetchLivescores(Constant.TOKEN).data
                Log.d("Api", "Livescores: ${livescores.value?.size}")
            }
            catch (e: java.lang.Exception) {
                _livescores.value = listOf()
                Log.d("Exception","$e")
            }
        }
    }

}