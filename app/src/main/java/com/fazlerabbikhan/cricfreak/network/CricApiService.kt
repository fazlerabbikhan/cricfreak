package com.fazlerabbikhan.cricfreak.network

import com.fazlerabbikhan.cricfreak.global.Constant.Companion.BASE_URL
import com.fazlerabbikhan.cricfreak.model.fixtures.Fixture
import com.fazlerabbikhan.cricfreak.model.leagues.League
import com.fazlerabbikhan.cricfreak.model.livescores.Livescore
import com.fazlerabbikhan.cricfreak.model.players.Players
import com.fazlerabbikhan.cricfreak.model.rankings.Ranking
import com.fazlerabbikhan.cricfreak.model.teams.Team
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object CricApi {
    val retrofitService: CricApiService by lazy { retrofit.create(CricApiService::class.java) }
}

interface CricApiService {

    @GET("leagues")
    suspend fun fetchLeagues(
        @Query("api_token") apiKey: String
    ): League

    @GET("teams")
    suspend fun fetchTeams(
        @Query("api_token") apiKey: String
    ): Team

    @GET("players")
    suspend fun fetchPlayers(
        @Query("api_token") apiKey: String
    ): Players

    @GET("https://cricket.sportmonks.com/api/v2.0/fixtures?include=runs.team,venue,stage,season,league,scoreboards,referee,firstumpire,secondumpire,tvumpire,manofmatch,,tosswon,localteam,visitorteam,batting.batsman,bowling.bowler,lineup&filter[status]=NS&sort=starting_at")
    suspend fun fetchUpcoming(
        @Query("api_token") apiToken: String
    ): Fixture

    @GET("https://cricket.sportmonks.com/api/v2.0/fixtures?include=runs.team,venue,stage,season,league,scoreboards,referee,firstumpire,secondumpire,tvumpire,manofmatch,,tosswon,localteam,visitorteam,batting.batsman,bowling.bowler,lineup&filter[status]=Finished&filter[type]=T20I&sort=-starting_at")
    suspend fun fetchRecentInternational(
        @Query("api_token") apiToken: String
    ): Fixture

    @GET("https://cricket.sportmonks.com/api/v2.0/fixtures?include=runs.team,venue,stage,season,league,scoreboards,referee,firstumpire,secondumpire,tvumpire,manofmatch,,tosswon,localteam,visitorteam,batting.batsman,bowling.bowler,lineup&filter[status]=Finished&filter[type]=T20&sort=-starting_at")
    suspend fun fetchRecentDomestic(
        @Query("api_token") apiToken: String
    ): Fixture

    @GET("livescores")
    suspend fun fetchLivescores(
        @Query("api_token") apiKey: String
    ): Livescore

    @GET("team-rankings?filter[type]=TEST")
    suspend fun fetchTestRankings(
        @Query("api_token") apiKey: String
    ): Ranking

    @GET("team-rankings?filter[type]=ODI")
    suspend fun fetchODIRankings(
        @Query("api_token") apiKey: String
    ): Ranking

    @GET("team-rankings?filter[type]=T20I")
    suspend fun fetchT20IRankings(
        @Query("api_token") apiKey: String
    ): Ranking
}