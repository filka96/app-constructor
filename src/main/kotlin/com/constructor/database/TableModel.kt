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
    override val primaryKey = PrimaryKey(id, name = "PK_test_table_id")

    fun RouteToDTO (rr : ResultRow) : TableDTO
    {
        return TableDTO(id = rr[TableModel.id],
            stringField = rr[TableModel.strfield],
            intfield = rr[TableModel.intField],
            boolfield = rr[TableModel.boolField])
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

    fun Create(js : MutableList<TableDTO>)
    {
        for (el in js) {
            val temp_1 = el.id
            val temp_2 = el.stringField
            val temp_3 = el.intfield
            val temp_4 = el.boolfield

            transaction(db_conn) {
                TableModel.insert {
                    it[TableModel.id] = temp_1
                    it[TableModel.strfield] = temp_2
                    it[intField] = temp_3
                    it[boolField] = temp_4
                }
                commit()
            }
        }
    }

    fun Delete(id : UUID)
    {
        transaction(db_conn) {
            TableModel.deleteWhere { TableModel.id eq id }
            commit()
        }
    }

    fun Update(js : MutableList<TableDTO>)
    {
        for (el in js) {
            val temp_1 = el.id
            val temp_2 = el.stringField
            val temp_3 = el.intfield
            val temp_4 = el.boolfield

            transaction(db_conn) {
                TableModel.update({ TableModel.id eq temp_1 }) {
                    it[TableModel.strfield] = temp_2
                    it[intField] = temp_3
                    it[boolField] = temp_4
                }
                commit()
            }
        }
    }
}