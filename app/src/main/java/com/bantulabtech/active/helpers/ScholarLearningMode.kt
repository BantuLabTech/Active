package com.bantulabtech.active.helpers

enum class ScholarLearningMode {
    DAY,
    BOARDING
}

internal fun ScholarLearningMode.Description(): String = when(this){
    ScholarLearningMode.DAY->"DAY"
    ScholarLearningMode.BOARDING->"BOARDING"
}
