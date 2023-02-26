package com.fazlerabbikhan.cricfreak.global

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.fazlerabbikhan.cricfreak.model.fixtures.FixtureData
import com.fazlerabbikhan.cricfreak.model.teams.TeamData

class Constant {
    companion object {
        const val databaseName = "cricket_database"
        const val BASE_URL = "https://cricket.sportmonks.com/api/v2.0/"
        const val TOKEN = "F8hyrZdT673GlnBiSDo4ZHcebnruGUjBCH2v1Eh6YXDVjLfDr9OAlXHIrQxr"

//        const val COUNTRY_QUERY="${BASE_URL}countries?api_token=${TOKEN}"
//        const val LEAGUE_QUERY="${BASE_URL}leagues?api_token=${TOKEN}"
//        const val PLAYER_QUERY="${BASE_URL}players?api_token=${TOKEN}"
//        const val TEAM_QUERY="${BASE_URL}teams?api_token=${TOKEN}"
//        const val FIXTURE_QUERY="${BASE_URL}fixtures?api_token=${TOKEN}"
//        const val LIVE_SCORE_QUERY="${BASE_URL}livescores?api_token=${TOKEN}"

//        var category: String = Category.RECENT
        var recentDetails: FixtureData? = null
        var teamData: TeamData? = null

        @SuppressLint("StaticFieldLeak") var contextView: View? = null
        @SuppressLint("StaticFieldLeak") var context: Context? = null
    }
}