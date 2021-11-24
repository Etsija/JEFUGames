package com.etsija.jefugames.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_game",
    foreignKeys = arrayOf(
        ForeignKey(entity = Team::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_hometeam"),
            onDelete = ForeignKey.NO_ACTION),
        ForeignKey(entity = Team::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_awayteam"),
            onDelete = ForeignKey.NO_ACTION)
    )
)
data class Game(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    // Foreign keys with constraints

    val id_hometeam: Long,
    val id_awayteam: Long,

    val date_start: Long,
    val date_end: Long,
    val venue: String = "",
    val town: String = "",
    val home_score: Int,
    val away_score: Int,
    @ColumnInfo(name = "created_at")
    var createdAt: Long,
    @ColumnInfo(name = "modified_at")
    var modifiedAt: Long
)