package com.appconstructor.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID
import com.appconstructor.db_conn as db_conn1

object TableModel : Table("test_table") {
    private val id = uuid("id")
    private val strField = varchar("strfield", 30)
    private val intField = integer("intfield")
    private val boolField = bool("boolfield")
    override val primaryKey = PrimaryKey(id, name = "PK_test_table_id")

    private fun rowToDTO (rr : ResultRow) : TableDTO {
        return TableDTO(id = rr[id],
            stringfield = rr[strField],
            intfield = rr[intField],
            boolfield = rr[boolField])
    }

    fun readAll() : MutableList<TableDTO> {
        val temp : MutableList<TableDTO> = mutableListOf()
        transaction (db_conn1){
            val query = TableModel.selectAll()
            val result = query.toList()
            for(element in result) {
                temp.add(rowToDTO(element))
            }
        }
        return temp
    }

    fun create(js : MutableList<TableDTO>) {
        for (el in js) {
            val temp1 = el.id
            val temp2 = el.stringfield
            val temp3 = el.intfield
            val temp4 = el.boolfield

            transaction(db_conn1) {
                TableModel.insert {
                    it[id] = temp1
                    it[strField] = temp2
                    it[intField] = temp3
                    it[boolField] = temp4
                }
                commit()
            }
        }
    }

    fun delete(id : UUID) {
        transaction(db_conn1) {
            TableModel.deleteWhere { TableModel.id eq id }
            commit()
        }
    }

    fun update(js : MutableList<TableDTO>) {
        for (el in js) {
            val temp1 = el.id
            val temp2 = el.stringfield
            val temp3 = el.intfield
            val temp4 = el.boolfield

            transaction(db_conn1) {
                TableModel.update({ TableModel.id eq temp1 }) {
                    it[strField] = temp2
                    it[intField] = temp3
                    it[boolField] = temp4
                }
                commit()
            }
        }
    }
}