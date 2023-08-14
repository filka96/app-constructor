package com.appconstructor.customer.database.model

import com.appconstructor.UUIDSerial
import java.util.UUID
import kotlinx.serialization.Serializable


data class CustomerDTO(@Serializable(with = UUIDSerial.UUIDSerializer::class) val id : UUID,
                       val firstName : String, val lastName : String, val email : String)
