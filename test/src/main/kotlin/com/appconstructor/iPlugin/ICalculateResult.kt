package com.appconstructor.iPlugin

interface ICalculateResult<T> {
  fun setData(jsonFile : Map<String, Any>) : List<String>

  fun calculateResult(dataWidget: T): Any

  fun getResult(result : Any) : Any
}