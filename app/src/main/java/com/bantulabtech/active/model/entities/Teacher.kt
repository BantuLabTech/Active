package com.bantulabtech.active.model.entities

import com.bantulabtech.active.helpers.Gender
import java.util.*

data class Teacher (
    var id: UUID,
    var schoolId: String,
    var userId: UUID,
    var firstName: String,
    var lastName: String,
    var middleName: String,
    var gender: Gender
)