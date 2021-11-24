package com.etsija.jefugames.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_game_event_type")
data class GameEventType(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val description: String,
    val points: Int,
    @ColumnInfo(name = "created_at")
    var createdAt: Long,
    @ColumnInfo(name = "modified_at")
    var modifiedAt: Long
)