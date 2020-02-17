package com.bantulabtech.active.helpers

enum class SchoolAccreditationLevel {
    KINDERGARTEN,
    PRIMARY,
    BASIC,
    MIDDLE_BASIC,
    SECONDARY
}
internal fun SchoolAccreditationLevel.Description(): String = when(this){
    SchoolAccreditationLevel.KINDERGARTEN->"KINDERGARTEN"
    SchoolAccreditationLevel.PRIMARY->"PRIMARY"
    SchoolAccreditationLevel.BASIC->"BASIC"
    SchoolAccreditationLevel.MIDDLE_BASIC->"MIDDLE BASIC"
    SchoolAccreditationLevel.SECONDARY->"SECONDARY"

}