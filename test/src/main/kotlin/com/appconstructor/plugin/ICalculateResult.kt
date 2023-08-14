package com.appconstructor.plugin

interface ICalculateResult<T> {
  fun calculateResult(dataWidget: T) : Any
  fun getResult() : Any
}