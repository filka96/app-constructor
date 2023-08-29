package com.appconstructor.libs.server.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorInfo(
  val message: String,
)
