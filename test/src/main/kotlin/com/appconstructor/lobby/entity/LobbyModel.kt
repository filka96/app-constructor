package com.appconstructor.lobby.entity

import com.appconstructor.lobby.LobbyDTO
import java.util.UUID
import com.appconstructor.db_connEnvironment as db_conn
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object LobbyModel : Table("lobby") {
  private val id = uuid("id")
  private val title = varchar("title", 40)
  override val primaryKey = PrimaryKey(id, name = "PK_lobby_Id")

  private fun rowToDTO (rr : ResultRow) : LobbyDTO {
    return LobbyDTO(id = rr[id],
      name = rr[title])
  }
  fun getLobby(id : UUID) : MutableList<LobbyDTO>{
    val result = mutableListOf<LobbyDTO>()
    transaction(db_conn){
        val query = LobbyModel.select{ LobbyModel.id eq id}
        for (el in query){
            result.add(rowToDTO(el))
        }
    }
    return result
  }
  fun createLobby(js : LobbyDTO) {
    transaction(db_conn) {
      LobbyModel.insert{
        it[id] = js.id
        it[title] = js.name
      }
      commit()
    }
  }
}
