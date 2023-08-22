package com.appconstructor.app.database.entity

import com.appconstructor.app.database.model.AppDTO
import com.appconstructor.db_connEnvironment
import org.jetbrains.exposed.sql.transactions.transaction

fun getAllApp(): List<EntityApp> = transaction(db_connEnvironment) {
  EntityApp.all().toList()
}
fun createApp(jsonData : AppDTO) {
  transaction(db_connEnvironment) {
    EntityApp.new {
      title = jsonData.title
      description = jsonData.description
    }
  }
}