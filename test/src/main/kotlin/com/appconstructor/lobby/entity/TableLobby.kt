package com.appconstructor.lobby.entity

import org.jetbrains.exposed.dao.id.UUIDTable

object TableLobby : UUIDTable("lobby") {
  val title = varchar("title", 40)
}