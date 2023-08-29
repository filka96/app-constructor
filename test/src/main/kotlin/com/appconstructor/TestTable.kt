package com.appconstructor

import kotlinx.uuid.SecureRandom
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.autoGenerate
import kotlinx.uuid.exposed.kotlinxUUID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

class TestTable : Table("test_table") {
  val id: Column<UUID> = kotlinxUUID("id").autoGenerate(SecureRandom)
  val stringField: Column<String> = text("string_field")
  val intField: Column<Int> = integer("int_field")
  val boolField: Column<Boolean> = bool("bool_field")

  override val primaryKey = PrimaryKey(id)
}
