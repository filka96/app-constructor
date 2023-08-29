package com.appconstructor.libs.server

import io.ktor.server.application.ApplicationCall
import io.ktor.server.plugins.BadRequestException
import kotlinx.uuid.UUID

fun uuid(call: ApplicationCall, parameterName: String): UUID {
  val parameter = call.parameters[parameterName]
  if (parameter.isNullOrBlank()) {
    throw BadRequestException("$parameterName parameter is empty")
  }
  try {
    return UUID(parameter)
  } catch (e: Exception) {
    throw BadRequestException("cannot parse UUID", e)
  }
}
