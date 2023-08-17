package com.appconstructor.app.database.entity

import org.jetbrains.exposed.dao.id.UUIDTable

object TableApp : UUIDTable("app") {
  val title = text("title")
  val description = varchar("description", 250)
}
