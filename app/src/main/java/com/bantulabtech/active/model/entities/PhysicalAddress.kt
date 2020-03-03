package com.bantulabtech.active.model.entities

import androidx.room.PrimaryKey
import java.util.*

data class PhysicalAddress (
    @PrimaryKey var id: UUID,
    var streetAddress1: String,
    var streetAddress2: String,
    var postalCode: String,
    var city: String,
    var country: String,
    var primaryPhone: String,
    var secondaryPhone: String,
    var primaryEmail: String,
    var secondaryEmail: String
    )