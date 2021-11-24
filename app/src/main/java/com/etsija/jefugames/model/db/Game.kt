package com.etsija.jefugames.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_game")
data class Game(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    // Foreign keys
    val id_hometeam: Long,
    val id_awayteam: Long,

    val date_start: Long,
    val date_end: Long,
    val venue: String = "",
    val town: String = "",
    val home_score: Int,
    val away_score: Int
)