package com.appconstructor.lobby.entity

import com.appconstructor.db_connEnvironment
import com.appconstructor.lobby.model.LobbyDTO
import java.util.UUID
import org.jetbrains.exposed.sql.transactions.transaction

fun getLobby(idParameter : UUID) : EntityLobby? {
  var data : EntityLobby? = null
  transaction (db_connEnvironment){
     data = EntityLobby.findById(idParameter)
  }
  return data
}

fun createLobby(jsonFile : LobbyDTO) {
  transaction(db_connEnvironment){
    EntityLobby.new {
      title = jsonFile.title
      commit()
    }
  }
}

