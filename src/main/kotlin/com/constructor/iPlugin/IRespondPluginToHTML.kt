package com.constructor.iPlugin

import java.util.UUID

interface IRespondPluginToHTML {
  // отображает плагос в HTML у пользака
  fun showPlugin(id: UUID): Map<String, Any>

  fun hideInPluginList(acceptDisable : Boolean, id : String) : String{
    return "This plugin was uninstall"
  }
}