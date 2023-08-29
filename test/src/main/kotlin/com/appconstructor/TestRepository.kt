package com.appconstructor

import com.appconstructor.libs.db.CrudRepository
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder

class TestRepository(db: Database, table: TestTable) : CrudRepository<UUID, TestEntity, TestTable>(db, table) {
  override fun getId(table: TestTable) = table.id
  override fun getId(entity: TestEntity) = entity.id!!

  override fun toEntity(row: ResultRow): TestEntity {
    return TestEntity(
      id = row[table.id],
      stringField = row[table.stringField],
      intField = row[table.intField],
      boolField = row[table.boolField]
    )
  }

  override fun toRow(entity: TestEntity, row: UpdateBuilder<*>) {
    row[table.stringField] = entity.stringField
    row[table.intField] = entity.intField
    row[table.boolField] = entity.boolField
  }
}
