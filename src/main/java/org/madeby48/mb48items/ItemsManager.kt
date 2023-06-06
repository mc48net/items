package org.madeby48.mb48items

import org.madeby48.mb48items.db.*

class ItemsManager(private val plugin: Main) {

    fun items(): List<ItemEntity> = plugin.transaction { ItemEntity.all().toList() }

    fun getitem(id: Int): ItemEntity? = plugin.transaction { ItemEntity.findById(id) }

}