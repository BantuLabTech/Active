package com.bantulabtech.active.model.entities

import android.location.Location
import androidx.annotation.NonNull
import androidx.room.*
import com.google.android.gms.location.Geofence
import java.util.*

@Entity(
    tableName = "rooms",
    indices = [
        Index(
            value = ["room_name"],
            unique = true
        )
    ],
    foreignKeys = [
        ForeignKey(
            entity = Block::class,
            parentColumns = ["id"],
            childColumns = ["block_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Room(
    @PrimaryKey var id: UUID,
    @ColumnInfo(name = "room_name") var roomName: String,
    var description: String,
    @ColumnInfo(name = "block_id") var blockId: UUID,
    @Embedded @ColumnInfo(name = "room_dimenstion") var roomDimension: PhysicalDimension,
    @NonNull @ColumnInfo(name = "gps_location") var gpsLocation: Location,
    @NonNull @ColumnInfo(name = "geofence") var geofence: Geofence,
    var type: String
)