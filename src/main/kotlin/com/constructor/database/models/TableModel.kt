package com.constructor.database.models

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
object TableModel : Table ("test_table"){
    public val id = TableModel.integer("id").autoIncrement()
    public val stringfield = TableModel.varchar("stringfield", 30)
    private val intfield = TableModel.integer("intfield")
    private val boolfield = TableModel.bool("boolfield")

    fun insert(TableDTO : TableDTO) { // ввести данные в бд
        transaction {
            TableModel.insert {
                it[id] = TableDTO.id
                it[stringfield] = TableDTO.stringfield
                it[intfield] = TableDTO.intfield
                it[boolfield] = TableDTO.boolfield
            }
        }
    fun fetchTable(id: Int): TableDTO //извлечь
    {
        val tableModel = TableModel.select { TableModel.id.eq(id)}.single()
            return TableDTO(
                id = tableModel[TableModel.id],
                stringfield = tableModel[stringfield],
                intfield = tableModel[intfield],
                boolfield = tableModel[boolfield]
            )
    }
        }
    //TODO CRUD method's

    // READ
    // CREATE
    // DELETE
    // UPDATE
}