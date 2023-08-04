package com.appconstructor.iPlugin

class PluginManager {
  fun enablePlugin(plugin : IPlugin) : Boolean{
    plugin.getId()
    return true
  }

  fun disablePlugin(plugin: IPlugin) : Boolean{
    plugin.getId()
    return true
  }

  fun isAvailable(idUser : String) : Boolean{
    return true
  }
}