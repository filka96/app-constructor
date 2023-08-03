package com.appconstructor.libs.db

import java.sql.Connection
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transactionManager

private fun isProduction(): Boolean { // todo
  return false
}

private fun connectProduction(dbConfig: DbConfig): Database {
  throw RuntimeException("not implemented yet") // todo
}

private fun connectLocal(dbConfig: DbConfig): Database {
  val database = Database.connect("jdbc:h2:./${dbConfig.dbName};MODE=MYSQL", "org.h2.Driver")
  database.transactionManager.defaultIsolationLevel = Connection.TRANSACTION_READ_COMMITTED
  TransactionManager.defaultDatabase = database
  return database
}

fun connect(dbConfig: DbConfig) = if (isProduction()) {
  connectProduction(dbConfig)
} else {
  connectLocal(dbConfig)
}
