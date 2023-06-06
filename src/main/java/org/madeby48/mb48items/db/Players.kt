package org.madeby48.mb48items.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.util.UUID

class PlayerEntity(id: EntityID<Int>) : IntEntity(id) {

    var uuid: UUID by PlayersTable.uuid
    var name: String by PlayersTable.name

    companion object : IntEntityClass<PlayerEntity>(PlayersTable)
}

object PlayersTable : IntIdTable("players") {

    val uuid: Column<UUID> = uuid("uuid")
    val name: Column<String> = varchar("name", 16)

}
