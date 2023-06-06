package org.madeby48.mb48items.utils

import org.bukkit.inventory.ItemStack

enum class DBItem(val textFields: List<String>, val traits: HashMap<String, Boolean>, val itemStack: ItemStack)

fun writeToDatabase(item: DBItem) {

}