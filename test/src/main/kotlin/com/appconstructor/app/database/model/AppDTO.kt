package com.appconstructor.app.database.model

import kotlinx.serialization.Serializable

@Serializable
data class AppDTO(val title : String,
                  val description : String)
