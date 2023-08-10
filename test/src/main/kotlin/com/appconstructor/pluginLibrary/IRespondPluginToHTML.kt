package com.appconstructor.pluginLibrary

interface IRespondPluginToHTML {
  // отображает плагос в HTML у пользака
  fun showPlugin(idUser : String): Map<String, Any>

  fun hideInPluginList(idUser : String) : String
}