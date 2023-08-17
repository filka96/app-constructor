package com.appconstructor.customer.database.entity

import com.appconstructor.customer.database.model.CustomerDTO
import com.appconstructor.db_connEnvironment
import org.jetbrains.exposed.sql.transactions.transaction

// for debug and connection tables Many-to-Many
fun createCustomer(jsonData : CustomerDTO){
  transaction(db_connEnvironment){
    CustomerModel.new {
      firstName = jsonData.firstName
      lastName = jsonData.lastName
      email = jsonData.email
    }
  }
}