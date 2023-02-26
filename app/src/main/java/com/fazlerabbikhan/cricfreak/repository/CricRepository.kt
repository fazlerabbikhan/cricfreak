package com.fazlerabbikhan.cricfreak.repository

import androidx.lifecycle.LiveData
import com.fazlerabbikhan.cricfreak.database.CricDao
import com.fazlerabbikhan.cricfreak.model.leagues.LeagueData
import com.fazlerabbikhan.cricfreak.model.teams.TeamData

class CricRepository(private val cricDao: CricDao) {
    val readLeagueData: LiveData<List<LeagueData>> = cricDao.readLeagues()
    val readTeamData: LiveData<List<TeamData>> = cricDao.readTeams()

    suspend fun addLeague(leagues: List<LeagueData>){
        cricDao.addLeague(leagues)
    }
    suspend fun addTeam(teams: List<TeamData>){
        cricDao.addTeam(teams)
    }

//    fun readTeamById(id: Int): TeamEntity{
//        return cricDao.readTeamById(id)
//    }

}