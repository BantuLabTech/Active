package com.bantulabtech.active.helpers

enum class SchoolScholarGenderMode {
    GIRLS_ONLY,
    BOYS_ONLY,
    GIRLS_AND_BOYS
}
internal fun SchoolScholarGenderMode.Description(): String = when(this){
    SchoolScholarGenderMode.BOYS_ONLY->"BOYS ONLY"
    SchoolScholarGenderMode.GIRLS_AND_BOYS->"GIRLS AND BOYS"
    SchoolScholarGenderMode.GIRLS_ONLY->"GIRLS ONLY"
}