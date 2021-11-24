package com.etsija.jefugames.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_team")
data class Team(
    @PrimaryKey (autoGenerate = true)
    var id: Long,

    val name: String,
    val hometown: String? = "",
    val manager: String? = "",
    @ColumnInfo(name = "selected_for_game")
    val selectedForGame: Int = 0,
    @ColumnInfo(name = "created_at")
    var createdAt: Long,
    @ColumnInfo(name = "modified_at")
    var modifiedAt: Long
)