package com.bantulabtech.active.model.entities

import android.location.Location
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bantulabtech.active.helpers.SchoolAccreditationLevel
import com.bantulabtech.active.helpers.SchoolScholarGenderMode
import com.bantulabtech.active.helpers.SchoolLearningMode
import com.google.android.gms.location.Geofence
import java.util.*
import kotlin.collections.ArrayList

@Entity(
    tableName = "schools",
    indices = [
        Index(
        value = ["school_name"],
        unique = false
        ),
        Index(
            value = ["school_id"],
            unique = true
        )
    ]
)
data class School (
    @PrimaryKey var id: UUID,
    @ColumnInfo(name = "school_code") var schoolCode: String,
    @ColumnInfo(name = "school_name") @NonNull var schoolName: String,
    @ColumnInfo(name = "school_accreditation_levels")
    @NonNull var schoolAccreditationLevels: ArrayList<SchoolAccreditationLevel>,
    @ColumnInfo(name = "school_learning_mode")
    @NonNull var schoolLearningMode: SchoolLearningMode,
    @ColumnInfo(name = "school_scholar_gender_mode")
    @NonNull var schoolScholarGenderMode: SchoolScholarGenderMode,
    @ColumnInfo(name = "gps_location") @NonNull var gpsLocation: Location,
    @ColumnInfo(name = "geofence") @NonNull var geofence: Geofence,
    @ColumnInfo(name = "physical_address") @NonNull  var physical_address: PhysicalAddress,
    @ColumnInfo(name = "school_dimension") var schoolDimension: PhysicalDimension
)