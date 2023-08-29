package com.appconstructor.libs.server.response

import kotlinx.serialization.Serializable

@Serializable
data class OkResponse<T>(
  val body: T? = null,
)
