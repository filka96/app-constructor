package com.appconstructor.app.database.entity

import org.jetbrains.exposed.dao.id.UUIDTable

object TableApp : UUIDTable("app") {
  val title = varchar("title", 40)
  val description = varchar("description", 250)
}
