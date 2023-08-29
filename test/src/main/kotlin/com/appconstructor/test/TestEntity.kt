package com.appconstructor.test

import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

@Serializable
data class TestEntity(
  val id: UUID?,
  var stringField: String,
  var intField: Int,
  var boolField: Boolean
)
