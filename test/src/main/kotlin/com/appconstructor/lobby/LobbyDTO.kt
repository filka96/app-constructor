package com.appconstructor.lobby

import com.appconstructor.UUIDSerial
import java.util.UUID
import kotlinx.serialization.Serializable

data class LobbyDTO(@Serializable(with = UUIDSerial.UUIDSerializer::class) val id : UUID,
                                val name : String)
