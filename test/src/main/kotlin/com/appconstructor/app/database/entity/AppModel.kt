package com.appconstructor.app.database.entity

import com.appconstructor.app.database.model.AppDTO
import com.appconstructor.db_connEnvironment
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object AppModel : Table("app") {
  private val id = uuid("id")
  private val title = varchar("title", 40)
  private val description = varchar("description", 250)
  override val primaryKey = PrimaryKey(id, name = "PK_app_Id")

  private fun rowToDTO (rr : ResultRow) : AppDTO {
    return AppDTO(id = rr[id],
      title = rr[title],
      description = rr[description])
  }
  fun getAllApp() : MutableList<AppDTO>{
    val result = mutableListOf<AppDTO>()
    transaction(db_connEnvironment){
      val query = AppModel.selectAll()
      for (el in query){
        result.add(rowToDTO(el))
      }
    }
    return result
  }
  fun createLobby(js : AppDTO) {
    transaction(db_connEnvironment) {
      AppModel.insert{
        it[AppModel.id] = js.id
        it[AppModel.title] = js.title
        it[AppModel.description] = js.description
      }
      commit()
    }
  }
}