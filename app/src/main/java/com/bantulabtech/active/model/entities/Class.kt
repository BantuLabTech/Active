package com.bantulabtech.active.model.entities

import java.util.*

data class Class(
    val id: UUID,
    val sessions: List<Session>

)