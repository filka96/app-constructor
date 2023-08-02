package com.constructor.iPlugin

interface IPlugin {
    // плагин реализует только одну сущность!

    // задает создатель плагина
    fun setId(id : String)
    fun getId() : String

    // для отображения нормального имени плагина пользователю
    fun setName(name : String)
    fun getName() : String

    // задает имя автора плагина
    fun setAuthorName(nameAuthor : String)
    fun getAuthorName() : String

    // задает описание плагина
    fun setDescription(text : String)
    fun getDescription() : String

    // задает тип плагина из Enum
    fun setType(type : PluginType)
    fun getType() : String

    fun isAvailable(idUser : String) : Boolean

    // возвращает всю информацию о плагине
    fun getPluginInfo(name: String, type : PluginType, authorName : String,
                      isAvailable: Boolean, description: String) : Map<String, Any>
}