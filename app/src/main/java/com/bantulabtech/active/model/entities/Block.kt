package com.bantulabtech.active.model.entities

import androidx.room.*
import java.util.*

@Entity(
    tableName = "blocks",
    indices = [
        Index(
            value = ["block_name"],
            unique = true
        )
    ],
    foreignKeys = [
        ForeignKey(
            entity = School::class,
            parentColumns = ["id"],
            childColumns = ["school_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Block (
    @PrimaryKey var id: UUID,
    @Embedded @ColumnInfo(name = "block_dimenstion") var blockDimension: PhysicalDimension,
    @ColumnInfo(name = "school_id") var schoolId: UUID,
    @ColumnInfo(name = "block_name") var blockName: String,
    @ColumnInfo(name = "description") var description: String
)