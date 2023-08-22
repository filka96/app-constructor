package com.appconstructor.lobby.entity

import java.util.UUID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EntityLobby(id : EntityID<UUID>) : Entity<UUID>(id){
  companion object : EntityClass<UUID, EntityLobby>(TableLobby)

  var title by TableLobby.title
}