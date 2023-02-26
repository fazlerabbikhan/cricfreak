package com.fazlerabbikhan.cricfreak.model.leagues

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Leagues")
data class LeagueData(
    val code: String,
    val country_id: Int,
    @PrimaryKey val id: Int,
    val image_path: String,
    val name: String,
    val resource: String,
    val season_id: Int,
    val type: String,
    val updated_at: String
)