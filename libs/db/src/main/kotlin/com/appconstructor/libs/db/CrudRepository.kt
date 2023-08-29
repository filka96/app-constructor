package com.appconstructor.libs.db

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

abstract class CrudRepository<ID, ENTITY, TABLE : Table>(
  protected val db: Database,
  protected val table: TABLE,
) {
  abstract fun toEntity(row: ResultRow): ENTITY
  abstract fun toRow(entity: ENTITY, row: UpdateBuilder<*>)
  abstract fun getId(entity: ENTITY): ID
  abstract fun getId(table: TABLE): Column<ID>

  // CRUD Transactions
  fun create(entity: ENTITY) = transaction(db) { Statements.insert(entity) }
  fun readById(id: ID) = transaction(db) { Statements.selectById(id) }
  fun readAll() = transaction(db) { Statements.selectAll() }
  fun update(id: ID, entity: ENTITY) = transaction(db) { Statements.update(id, entity) }
  fun deleteById(id: ID) = transaction(db) { Statements.deleteById(id) }
  fun delete(entity: ENTITY) = deleteById(getId(entity))

  // Statements
  companion object Statements

  fun Statements.byId(id: ID) = getId(table).eq(id)

  fun Statements.insert(entity: ENTITY): ENTITY {
    val statement = table.insert { toRow(entity, it) }
    return toEntity(statement.resultedValues!!.first())
  }

  fun Statements.selectById(id: ID) = table.select { byId(id) }.map(::toEntity).singleOrNull()

  fun Statements.selectAll() = table.selectAll().map(::toEntity).toList()

  fun Statements.update(id: ID, entity: ENTITY): ENTITY {
    table.update(
      where = { byId(id) },
      body = { toRow(entity, it) },
    )
    return selectById(id)!!
  }

  fun Statements.deleteById(id: ID) = table.deleteWhere { byId(id) }
}
