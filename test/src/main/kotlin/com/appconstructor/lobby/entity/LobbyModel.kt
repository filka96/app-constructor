package com.appconstructor.lobby.entity

import java.util.UUID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object LobbyModels : UUIDTable("lobby") {
  val title = varchar("title", 40)
}

class LobbyModel(id : EntityID<UUID>) : Entity<UUID>(id){
  companion object : EntityClass<UUID, LobbyModel>(LobbyModels)

  var title by LobbyModels.title
}