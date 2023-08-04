package com.appconstructor.iPlugin

interface IRespondPluginToHTML {
  // отображает плагос в HTML у пользака
  fun showPlugin(id: String): Map<String, Any>

  fun hideInPluginList(acceptDisable : Boolean, id : String) : String{
    return "This plugin was uninstall"
  }
}