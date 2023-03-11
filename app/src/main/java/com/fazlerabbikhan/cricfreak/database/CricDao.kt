package com.fazlerabbikhan.cricfreak.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fazlerabbikhan.cricfreak.model.leagues.LeagueData
import com.fazlerabbikhan.cricfreak.model.teams.TeamData

@Dao
interface CricDao {

    @Query("SELECT * FROM Leagues")
    fun readLeagues(): LiveData<List<LeagueData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLeague(leagues: List<LeagueData>)

    @Query("SELECT * FROM Teams")
    fun readTeams(): LiveData<List<TeamData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTeam(teams: List<TeamData>)
}