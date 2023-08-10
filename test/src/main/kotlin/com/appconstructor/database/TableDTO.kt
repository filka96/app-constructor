package com.appconstructor.database

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class TableDTO (@Serializable(with = UUIDSerial.UUIDSerializer::class) val id : UUID,
                     val strfield : String,
                     val intfield : Int, val boolfield : Boolean)