package com.etsija.jefugames.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_game_event")
data class GameEvent(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    // Foreign keys
    val id_game: Long,
    val id_team: Long,
    val id_event_type: Long,

    val gametime: Int,
    @ColumnInfo(name = "created_at")
    var createdAt: Long,
    @ColumnInfo(name = "modified_at")
    var modifiedAt: Long
)