package com.appconstructor.test.database.entity

import java.util.UUID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object TableTestModels : UUIDTable("test_table") {
    val strField = varchar("str_field", 30)
    val intField = integer("int_field")
    val boolField = bool("bool_field")
}

class TableTestModel(id : EntityID<UUID>) : Entity<UUID>(id){
    companion object : EntityClass<UUID, TableTestModel>(TableTestModels)

    var strField by TableTestModels.strField
    var intField by TableTestModels.intField
    var boolField by TableTestModels.boolField
}