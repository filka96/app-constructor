package com.constructor.iPlugin

class PluginManager {
  fun ablePlugin(id: String) : Boolean{
    return true
  }

  fun disablePlugin(id : String) : Boolean{
    return true
  }

  fun addToPluginDatabase(pluginInfo : Map<String, Any>) {}

  fun registerPluginInUserDatabase(userAcceptToInstall : Any, id : String) {}
  fun setResultToDatabase(dataResult : Any){}
}