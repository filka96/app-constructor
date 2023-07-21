package com.constructor.database

import com.constructor.db_conn
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

object TableModel : Table("test_table") {
    val id = uuid("id")// must be uuid
    val strfield = varchar("strfield", 30)
    val intField = integer("intfield")
    val boolField = bool("boolfield")
    //var jsonfield =
    //val jsonstring = Json.decodeFromString<JsonField>(jsonfield)
    override val primaryKey = PrimaryKey(id, name = "PK_test_table_id")

    fun RouteToDTO (rr : ResultRow) : TableDTO
    {
        return TableDTO(id = rr[TableModel.id],
            stringField = rr[TableModel.strfield],
            intfield = rr[TableModel.intField],
            boolfield = rr[TableModel.boolField])
            //jsonfield = rr[TableModel.jsonfield]
    }

    // crud
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

    fun insert(id : UUID, strField : String, intfield : Int, boolfield : Boolean)
    {
        transaction (db_conn){
            TableModel.insert {
                it[TableModel.id] = id
                it[TableModel.strfield] = strField
                it[intField] = intfield
                it[boolField] = boolfield
            }
            commit()
        }
    }

    fun delete(id : UUID)
    {
        transaction(db_conn) {
            TableModel.deleteWhere { TableModel.id eq id }
            commit()
        }
    }

    // возвращаем объект Table
    fun update(id : UUID, stringField: String)
    {
        transaction(db_conn){
            TableModel.update({TableModel.id eq id}){
                it[TableModel.strfield] = stringField
            }
            commit()
        }
    }
}