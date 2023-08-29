package com.appconstructor.libs.server.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
  val error: ErrorInfo,
)
