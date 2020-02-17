package com.bantulabtech.active.helpers

enum class SchoolLearningMode {
    DAY_ONLY,
    BOARDING_ONLY,
    DAY_AND_BOARDING
}
internal fun SchoolLearningMode.Description(): String = when(this){
    SchoolLearningMode.DAY_AND_BOARDING->"DAY AND BOARDING"
    SchoolLearningMode.BOARDING_ONLY->"BOARDING ONLY"
    SchoolLearningMode.DAY_ONLY->"DAY ONLY"
}