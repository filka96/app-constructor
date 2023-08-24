package com.appconstructor.test.database.model

import kotlinx.serialization.Serializable

@Serializable
data class TableTestDTO (val strField : String,
                         val intField : Int,
                         val boolField : Boolean)