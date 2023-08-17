package com.appconstructor.app.database

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

open class CrudRepository<Id : Comparable<Id>, E : Entity<Id>, C : EntityClass<Id, E>>(
  private val db: Database,
  private val dao: C,
) {
  fun getAll(): List<E> = transaction(db) {
    dao.all().toList()
  }
}
