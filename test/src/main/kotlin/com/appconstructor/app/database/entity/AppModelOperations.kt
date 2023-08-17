package com.appconstructor.app.database.entity

import com.appconstructor.db_connEnvironment
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction

fun getAllApp() : List<AppModel>?{
  var data : SizedIterable<AppModel>? = null
  transaction(db_connEnvironment){
    data = AppModel.all()
  }
  return data?.toList()
}