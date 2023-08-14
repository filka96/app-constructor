package com.appconstructor.customer.database.entity

import com.appconstructor.customer.database.model.CustomerDTO
import com.appconstructor.db_connEnvironment
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object CustomerModel : Table("customer"){
  private val id = uuid("id")
  private val firstName = varchar("first_name", 30)
  private val lastName = varchar("last_name", 40)
  private val email = varchar("email", 40)
  override val primaryKey = PrimaryKey(id, name = "PK_customer_Id")

  private fun rowToDTO (rr : ResultRow) : CustomerDTO {
    return CustomerDTO(id = rr[id],
      firstName = rr[firstName],
      lastName = rr[lastName],
      email = rr[email])
  }
  fun getAllApp() : MutableList<CustomerDTO>{
    val result = mutableListOf<CustomerDTO>()
    transaction(db_connEnvironment){
      val query = CustomerModel.selectAll()
      for (el in query){
        result.add(rowToDTO(el))
      }
    }
    return result
  }
  fun createLobby(js : CustomerDTO) {
    transaction(db_connEnvironment) {
      CustomerModel.insert{
        it[id] = js.id
        it[firstName] = js.firstName
        it[lastName] = js.lastName
        it[email] = js.email
      }
      commit()
    }
  }
}