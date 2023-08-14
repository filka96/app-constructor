package com.appconstructor.app.database.model

import com.appconstructor.UUIDSerial
import java.util.UUID
import kotlinx.serialization.Serializable

data class AppDTO(@Serializable(with = UUIDSerial.UUIDSerializer::class) val id : UUID,
                  val title : String, val description : String)
