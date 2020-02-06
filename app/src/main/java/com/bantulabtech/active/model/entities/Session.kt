package com.bantulabtech.active.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "session")
data class Session(
    @PrimaryKey
    val id: UUID,
    @ColumnInfo(name = "start_time")
    val startTime: Float,
    @ColumnInfo(name = "end_time")
    val endTime: Float,
    @ColumnInfo(name = "start_date")
    val startDate: Date,
    @ColumnInfo(name = "end_date")
    val endDate: Date,
    val type: String
)