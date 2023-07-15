package com.constructor.database.models

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
object TableModel : Table ("test_table"){
    private val id = TableModel.integer("id").autoIncrement()
    private val stringfield = TableModel.varchar("stringfield", 30)
    private val intfield = TableModel.integer("intfield")
    private val boolfield = TableModel.bool("boolfield")

    // TODO CRUD method's

    // TODO Create query
    fun Create(TableDTO : TableDTO) { // ввести данные в бд
        try {
            transaction {
                val q = TableModel.insert {
                    it[id] = TableDTO.id
                    it[stringfield] = TableDTO.stringfield
                    it[intfield] = TableDTO.intfield
                    it[boolfield] = TableDTO.boolfield
                    commit() // сохраняем состояние и закрываем транзакцию
                }
                println(q)
            }
        }
        catch(e : Exception){
            println(e.message)
        }
    }
    // TODO Read query
    fun Read(TableDTO: TableDTO, id:Int) : TableDTO{
        val tableModel = TableModel.select { TableModel.id.eq(id) }.single()
        return TableDTO(
            id = tableModel[TableModel.id],
            stringfield = tableModel[stringfield],
            intfield = tableModel[intfield],
            boolfield = tableModel[boolfield]
        )
    }

    // TODO Delete query

    /*fun Delete(id: Int): TableDTO
    {
        val tableModel = TableModel.deleteWhere { TableModel.id.eq(id)}.single()
        return TableDTO(
            id = tableModel[TableModel.id],
            stringfield = tableModel[stringfield],
            intfield = tableModel[intfield],
            boolfield = tableModel[boolfield]
        )
    }*/
    // TODO Update
}