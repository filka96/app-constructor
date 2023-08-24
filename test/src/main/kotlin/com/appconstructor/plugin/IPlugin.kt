package com.appconstructor.plugin

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
    fun getType() : String

    // возвращает всю информацию о плагине
    fun getPluginInfo() : Map<String, Any>
}