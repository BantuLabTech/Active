package com.bantulabtech.active.helpers

enum class SchoolGenderMode {
    GIRLS_ONLY,
    BOYS_ONLY,
    GIRLS_AND_BOYS
}
internal fun SchoolGenderMode.Description(): String = when(this){
    SchoolGenderMode.BOYS_ONLY->"BOYS ONLY"
    SchoolGenderMode.GIRLS_AND_BOYS->"GIRLS AND BOYS"
    SchoolGenderMode.GIRLS_ONLY->"GIRLS ONLY"
}