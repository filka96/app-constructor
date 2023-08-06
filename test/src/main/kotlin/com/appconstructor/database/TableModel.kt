package com.appconstructor.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.*
import java.util.*
import com.appconstructor.db_conn as db_conn1

object TableModel : Table("test_table") {
    private val id = uuid("id")
    private val strField = varchar("strfield", 30)
    private val intField = integer("intfield")
    private val boolField = bool("boolfield")
    override val primaryKey = PrimaryKey(id, name = "PK_test_table_id")

    private fun rowToDTO (rr : ResultRow) : TableDTO {
        return TableDTO(id = rr[id],
            strfield = rr[strField],
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

    fun readOneRow(id: UUID) : MutableList<TableDTO>{
        val temp : MutableList<TableDTO> = mutableListOf()
        transaction(db_conn1){
            val query = TableModel.select(TableModel.id eq id)
            val result = query.toList()
            for (element in result)
            {
                temp.add(rowToDTO(element))
            }
        }
        return temp
    }

    fun create(js : TableDTO) {
        transaction(db_conn1) {
            TableModel.insert {
                it[id] = js.id
                it[strField] = js.strfield
                it[intField] = js.intfield
                it[boolField] = js.boolfield
            }
            commit()
        }
    }

    fun delete(id : UUID){
        transaction(db_conn1) {
            TableModel.deleteWhere { (TableModel.id eq id) }
        }
    }

    fun update(js : TableDTO) {
        // может инициализацию в отдельный метод засунуть?

        transaction(db_conn1) {
            TableModel.update({ TableModel.id eq js.id }) {
                it[strField] = js.strfield
                it[intField] = js.intfield
                it[boolField] = js.boolfield
            }
            commit()
        }
    }
}