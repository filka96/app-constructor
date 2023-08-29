package com.appconstructor.libs.db

data class DbConfig(
  val dbName: String,
  val user: String = "login",
  val password: String = "password",
)
