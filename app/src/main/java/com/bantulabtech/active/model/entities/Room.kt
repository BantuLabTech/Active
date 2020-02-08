package com.bantulabtech.active.model.entities

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "room")
data class Room(
    @PrimaryKey
    val id: UUID,
    val name: String,
    @ColumnInfo(name = "block_id")
    val blockId: UUID,
    val width: Float,
    val length: Float,
    @Embedded
    val location: Location,
    val type: String
)