package com.appconstructor.properties

import java.io.FileInputStream
import java.util.Properties

class PropertyReader{
  private val prop = Properties()
  private val pathFile = "test/src/main/resources/all.properties"

  // должен автоматически искать properties (насколько я понял), но оно не работает, а только
  // стреляет в ногу
  //var pathFile2 = this.javaClass.classLoader.getResource("all.properties")?.readText()

  // database
  fun getDriver() : String{
    FileInputStream(pathFile).use { prop.load(it) }
    return prop.getProperty("driver")
  }

  fun getUrlTest() : String{
    FileInputStream(pathFile).use { prop.load(it) }
    return prop.getProperty("urlTest")
  }

  fun getUrlLobby() : String{
    FileInputStream(pathFile).use { prop.load(it) }
    return prop.getProperty("urlLobby")
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