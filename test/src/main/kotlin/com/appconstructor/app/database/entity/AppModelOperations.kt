package com.appconstructor.app.database.entity

import com.appconstructor.app.database.model.AppDTO
import com.appconstructor.dbLobby
import java.util.UUID
import org.jetbrains.exposed.sql.transactions.transaction

fun getAllApp(): List<EntityApp> = transaction(dbLobby) {
  EntityApp.all().toList()
}
fun getOneApp(idParameter : UUID): EntityApp? = transaction(dbLobby){
  EntityApp.findById(idParameter)
}
fun createApp(jsonData : AppDTO) {
  transaction(dbLobby) {
    EntityApp.new {
      title = jsonData.title
      description = jsonData.description
    }
  }
}