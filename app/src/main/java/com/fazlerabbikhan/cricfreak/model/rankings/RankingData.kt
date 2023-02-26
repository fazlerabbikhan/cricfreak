package com.fazlerabbikhan.cricfreak.model.rankings

data class RankingData(
    val gender: String?,
    val points: Any?,
    val position: Any?,
    val rating: Any?,
    val resource: String?,
    val team: List<Team>?,
    val type: String?,
    val updated_at: String?
)