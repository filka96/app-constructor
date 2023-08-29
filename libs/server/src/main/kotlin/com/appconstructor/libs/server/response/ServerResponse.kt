package com.appconstructor.libs.server.response

class ServerResponse {
  companion object {
    fun ok(): OkResponse<Nothing> = OkResponse()
    fun <T> ok(t: T): OkResponse<T> = OkResponse(t)
    fun error(errorInfo: ErrorInfo): ErrorResponse = ErrorResponse(errorInfo)
    fun error(message: String): ErrorResponse = error(ErrorInfo(message = message))
    fun error(exception: Throwable): ErrorResponse = error(message = exception.localizedMessage)
  }
}
