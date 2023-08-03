package com.constructor.iPlugin

interface ICalculateResult<T> {
  fun setData(jsonFile : Map<String, Any>) : List<String>

  fun calculateResult(dataWidget: T): Any{
    // логика подсчета результатов
    return "CalculeResult"
  }

  fun getResult(result : Any) : Any{
    return "Result"
  }
}