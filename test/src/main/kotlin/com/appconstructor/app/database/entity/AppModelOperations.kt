package com.appconstructor.app.database.entity

import com.appconstructor.app.database.model.AppDTO
import com.appconstructor.dbLobby
import org.jetbrains.exposed.sql.transactions.transaction

fun getAllApp(): List<EntityApp> = transaction(dbLobby) {
  EntityApp.all().toList()
}
fun createApp(jsonData : AppDTO) {
  transaction(dbLobby) {
    EntityApp.new {
      title = jsonData.title
      description = jsonData.description
    }
  }
}