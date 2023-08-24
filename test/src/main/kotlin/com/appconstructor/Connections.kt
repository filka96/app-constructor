package com.appconstructor

import org.jetbrains.exposed.sql.Database

class Connections {
  fun testConnection(url : String, driver : String, user : String, password : String) : Database{
    return Database.connect(
      url = url,
      driver = driver,
      user = user,
      password = password
    )
  }
  fun lobbyConnection(url : String, driver : String, user : String, password : String) : Database{
    return Database.connect(
      url = url,
      driver = driver,
      user = user,
      password = password
    )
  }
}