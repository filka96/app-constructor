package com.appconstructor.pluginLibrary

interface ICalculateResult<T> {
  fun calculateResult(dataWidget: T) : Any
  fun getResult() : Any
}