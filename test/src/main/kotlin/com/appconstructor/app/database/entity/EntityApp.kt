package com.appconstructor.app.database.entity

import java.util.UUID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EntityApp(id : EntityID<UUID>) : Entity<UUID>(id){
  companion object : EntityClass<UUID, EntityApp>(TableApp)

  var title by TableApp.title
  var description by TableApp.description
}