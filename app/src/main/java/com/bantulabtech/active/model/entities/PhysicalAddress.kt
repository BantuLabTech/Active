package com.bantulabtech.active.model.entities

import androidx.room.PrimaryKey
import java.util.*

data class PhysicalAddress (
    @PrimaryKey var id: UUID,
    var streetAddress: String,
    var primaryPhone: String,
    var secondaryPhone: String,
    var primaryEmail: String,
    var secondaryEmail: String
    )