package com.constructor.database.models

import org.jetbrains.exposed.sql.Table
object TableModel : Table ("test_shop"){
    private val id = TableModel.integer("id")
    private val stringfield = TableModel.varchar("stringfield", 30)
    private val intfield = TableModel.integer("intfield")
    private val boolfield = TableModel.bool("boolfield")

    //TODO CRUD method's

    // READ
    // CREATE
    // DELETE
    // UPDATE
}