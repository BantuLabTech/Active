package com.bantulabtech.active.helpers

enum class Gender {
    MALE,
    FEMALE,
    UNKNOWN
}

internal fun Gender.Description(): String = when(this){
    Gender.MALE->"Male"
    Gender.FEMALE->"Female"
    Gender.UNKNOWN->"Unknown"
}