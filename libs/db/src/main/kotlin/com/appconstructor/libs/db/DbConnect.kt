package com.appconstructor.libs.db

import java.sql.Connection
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transactionManager

private fun isProduction(): Boolean { // todo: get from env
  return false
}

private fun connectProduction(dbConfig: DbConfig): Database {
  throw Error("cannot connect to ${dbConfig.dbName}, cause productions connection is not implemented yet") // todo
}

private fun connectLocal(dbConfig: DbConfig): Database {
  val database = Database.connect(
    url = "jdbc:h2:./${dbConfig.dbName};MODE=PostgreSQL",
    driver = "org.h2.Driver",
    user = dbConfig.user,
    password = dbConfig.password,
  )
  database.transactionManager.defaultIsolationLevel = Connection.TRANSACTION_READ_COMMITTED
  TransactionManager.defaultDatabase = database
  return database
}

fun connect(dbConfig: DbConfig) = if (isProduction()) connectProduction(dbConfig) else connectLocal(dbConfig)
