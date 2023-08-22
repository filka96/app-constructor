package com.appconstructor.lobby.entity

import com.appconstructor.dbLobby
import com.appconstructor.lobby.model.LobbyDTO
import java.util.UUID
import org.jetbrains.exposed.sql.transactions.transaction

fun getLobby(idParameter : UUID) : EntityLobby? {
  var data : EntityLobby? = null
  transaction (dbLobby){
     data = EntityLobby.findById(idParameter)
  }
  return data
}

fun createLobby(jsonFile : LobbyDTO) {
  transaction(dbLobby){
    EntityLobby.new {
      title = jsonFile.title
      commit()
    }
  }
}

