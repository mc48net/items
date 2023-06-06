package org.madeby48.mb48items.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

class ItemEntity(id: EntityID<Int>) : IntEntity(id) {

    var itemID: Int by ItemsTable.itemID
    var countGiven: Int by ItemsTable.countGiven
    var itemType: String by ItemsTable.itemType
    var itemLore: String by ItemsTable.itemLore
    var linkedScript: String by ItemsTable.linkedScript
    var lastModified: Instant by ItemsTable.lastModified

    companion object : IntEntityClass<ItemEntity>(ItemsTable)
}

object ItemsTable : IntIdTable("items") {

    val itemID: Column<Int> = integer("item_id")
    val countGiven: Column<Int> = integer("count_given")
    val itemType: Column<String> = text("item_type")
    val itemLore: Column<String> = text("item_lore")
    val linkedScript: Column<String> = text("linked_script")
    val lastModified: Column<Instant> = timestamp("time").clientDefault { Instant.now() }

}
