package com.constructor.database

import com.constructor.db_conn
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object TableModel : Table("test_table") {
    val id = integer("id").autoIncrement() // must be uuid
    val stringField = varchar("stringfield", 30)
    val intField = integer("intfield")
    val boolField = bool("boolfield")
    // jsonField = collection - map
    override val primaryKey = PrimaryKey(id, name = "PK_test_table_id")

    // crud
    fun RouteToDTO (rr : ResultRow) : TableDTO
    {
        return TableDTO(id = rr[TableModel.id],
            stringField = rr[TableModel.stringField],
            intfield = rr[TableModel.intField],
            boolfield = rr[TableModel.boolField])
    }

    fun readAll() : MutableList<TableDTO>
    {
        val temp : MutableList<TableDTO> = mutableListOf()
        transaction (db_conn){
            val query = TableModel.selectAll()
            val result = query.toList()
            for(element in result)
            {
                temp.add(RouteToDTO(element))
            }
        }
        return temp
    }

    fun insert(id : Int, stringField : String, intfield : Int, boolfield : Boolean)
    {
        transaction (db_conn){
            TableModel.insert {
                it[TableModel.id] = id
                it[TableModel.stringField] = stringField
                it[intField] = intfield
                it[boolField] = boolfield
            }
            commit()
        }
    }

    fun delete(id : Int)
    {
        transaction(db_conn) {
            TableModel.deleteWhere { TableModel.id eq id }
            commit()
        }
    }
}