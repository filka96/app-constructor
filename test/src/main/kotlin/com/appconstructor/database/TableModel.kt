package com.appconstructor.database

import com.appconstructor.db_conn
import java.util.UUID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object TableModel : Table("test_table") {
  val id = uuid("id")// must be uuid
  val strfield = varchar("strfield", 30)
  val intField = integer("intfield")
  val boolField = bool("boolfield")

  //var jsonfield =
  //val jsonstring = Json.decodeFromString<JsonField>(jsonfield)
  override val primaryKey = PrimaryKey(id, name = "PK_test_table_id")

  fun RouteToDTO(rr: ResultRow): TableDTO {
    return TableDTO(
      id = rr[id],
      stringField = rr[strfield],
      intfield = rr[intField],
      boolfield = rr[boolField]
    )
    //jsonfield = rr[TableModel.jsonfield]
  }

  // crud
  fun readAll(): MutableList<TableDTO> {
    val temp: MutableList<TableDTO> = mutableListOf()
    transaction(db_conn) {
      val query = TableModel.selectAll()
      val result = query.toList()
      for (element in result) {
        temp.add(RouteToDTO(element))
      }
    }
    return temp
  }

  fun insert(id: UUID, strField: String, intfield: Int, boolfield: Boolean) {
    transaction(db_conn) {
      TableModel.insert {
        it[TableModel.id] = id
        it[strfield] = strField
        it[intField] = intfield
        it[boolField] = boolfield
      }
      commit()
    }
  }

  fun delete(id: UUID) {
    transaction(db_conn) {
      TableModel.deleteWhere { TableModel.id eq id }
      commit()
    }
  }

  // нужно ли писать перегрузки?
  fun update(id: UUID, stringField: String) {
    transaction(db_conn) {
      TableModel.update({ TableModel.id eq id }) {
        it[strfield] = stringField
      }
      commit()
    }
  }
}
