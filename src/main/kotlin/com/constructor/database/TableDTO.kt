package com.constructor.database

import com.eaio.uuid.UUID
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.UUIDColumnType

data class TableDTO (val id : java.util.UUID, val stringField : String,
                     val intfield : Int, val boolfield : Boolean)
                     //val jsonfield : MutableMap<String, String>)

@Serializable
data class JsonField (val key : String, val value : String)