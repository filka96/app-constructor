package com.appconstructor.customer.database.entity

import java.util.UUID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object CustomerModels : UUIDTable("customer"){
  val firstName = varchar("first_name", 30)
  val lastName = varchar("last_name", 40)
  val email = varchar("email", 40)
}

class CustomerModel(id : EntityID<UUID>) : Entity<UUID>(id){
  companion object : EntityClass<UUID, CustomerModel>(CustomerModels)

  var firstName by CustomerModels.firstName
  var lastName by CustomerModels.lastName
  var email by CustomerModels.email
}

