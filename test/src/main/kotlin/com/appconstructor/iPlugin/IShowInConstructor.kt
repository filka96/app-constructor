package com.appconstructor.iPlugin

import kotlinx.html.HTML

interface IShowInConstructor {
  fun showInConstructorPanelPluginIcon(id: String, typePlugin: String, isAvailable: Boolean): HTML // отображает
  // изображение виджета, если это кончено виджет, в панели конструктора.

  fun deleteFromConstructorPanelPluginIcon(id: String, isAvailable: Boolean) : HTML

  fun relocateWidgetOnCanvas(id: String, isAvailable: Boolean, event : Any)
  // если это виджет, то его из панели
  // конструктора можно перетащить в canvas.

  fun addToCanvas(id : String, isAvailable: Boolean, event : Any) // добавляет виджет на canvas
  fun deleteFromCanvas(id: String, event : Any)
}