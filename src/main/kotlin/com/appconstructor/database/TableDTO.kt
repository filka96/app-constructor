package com.appconstructor.database

import kotlinx.serialization.Serializable

data class TableDTO(
  val id: java.util.UUID,
  val stringField: String,
  val intfield: Int,
  val boolfield: Boolean
)
//val jsonfield : MutableMap<String, String>)

@Serializable
data class JsonField(val key: String, val value: String)
