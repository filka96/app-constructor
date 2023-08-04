package com.appconstructor.iPlugin

interface IPlugin {
    // плагин реализует только одну сущность!

    // задает создатель плагина
    fun getId() : String

    // для отображения нормального имени плагина пользователю
    fun getName() : String

    // задает имя автора плагина
    fun getAuthorName() : String

    // задает описание плагина
    fun getDescription() : String

    // задает тип плагина из Enum
    fun getType(type: PluginType) : String

    // возвращает всю информацию о плагине
    fun getPluginInfo(name: String, type : PluginType, authorName : String,
                      isAvailable: Boolean, description: String) : Map<String, Any>
}