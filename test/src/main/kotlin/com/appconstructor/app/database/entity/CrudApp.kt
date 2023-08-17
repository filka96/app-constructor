package com.appconstructor.app.database.entity

import com.appconstructor.app.database.CrudRepository
import java.util.UUID
import org.jetbrains.exposed.sql.Database

class CrudApp(
  private val db: Database,
  private val dao: EntityApp.Companion,
) : CrudRepository<UUID, EntityApp, EntityApp.Companion>(
  db = db,
  dao = dao,
)

