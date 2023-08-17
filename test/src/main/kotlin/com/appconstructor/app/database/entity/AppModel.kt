package com.appconstructor.app.database.entity

import java.util.UUID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object AppModels : UUIDTable("app") {
  val title = varchar("title", 40)
  val description = varchar("description", 250)
}

class AppModel(id : EntityID<UUID>) : Entity<UUID>(id){
  companion object : EntityClass<UUID, AppModel>(AppModels)

  var title by AppModels.title
  var description by AppModels.description
}