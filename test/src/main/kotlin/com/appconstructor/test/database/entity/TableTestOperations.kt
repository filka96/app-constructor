package com.appconstructor.test.database.entity

import com.appconstructor.db_connTest
import com.appconstructor.test.database.model.TableTestDTO
import java.util.UUID
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction

fun getAllTableTest() : List<TableTestModel>?{
  var data : SizedIterable<TableTestModel>? = null
  transaction(db_connTest){
    data = TableTestModel.all()
  }
  return data?.toList()
}

fun getTableTest(idParameter : UUID) : TableTestModel? {
  var data : TableTestModel? = null
  transaction(db_connTest){
    data = TableTestModel.findById(idParameter)
  }
  return data
}

fun createRow(jsonData : TableTestDTO) {
  transaction(db_connTest){
    TableTestModel.new {
      strField = jsonData.strField
      intField = jsonData.intField
      boolField = jsonData.boolField
      commit()
    }
  }
}

fun updateRow(idParameter: UUID, jsonData : TableTestDTO) : Boolean{
  var isUpdated = false
  transaction(db_connTest){
    val data = TableTestModel.findById(idParameter)
    if(data != null){
      isUpdated = true
      data.strField = jsonData.strField
      data.intField = jsonData.intField
      data.boolField = jsonData.boolField
    }
    else{
      isUpdated = false
    }
  }
  return isUpdated
}

fun deleteRow(idParameter: UUID) : Boolean{
  var isDeleted = false
  transaction(db_connTest){
    val data = TableTestModel.findById(idParameter)
    if(data != null){
      isDeleted = true
      data.delete()
    }
    else{
      isDeleted = false
    }
  }
  return isDeleted
}