package com.appconstructor.databaseProperties

import java.io.FileInputStream
import java.util.Properties

class DatabaseProperties(pathFile : String) {
  private val prop = Properties()
  private var pathFile : String

  init {
    this.pathFile = pathFile
  }

  fun getLogin() : String{
    FileInputStream(pathFile).use { prop.load(it) }
    return prop.getProperty("login")
  }

  fun getPassword() : String{
    FileInputStream(pathFile).use { prop.load(it) }
    return prop.getProperty("password")
  }
}