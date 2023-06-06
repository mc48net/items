package org.madeby48.mb48items.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

class TrackerEntity(id: EntityID<Int>) : IntEntity(id) {

    var player: PlayerEntity by PlayerEntity referencedOn TrackerTable.player
    var time_given: Instant by TrackerTable.time_given
    var locationGiven: String by TrackerTable.location_given
    var item_id: Int by TrackerTable.item_id

    companion object : IntEntityClass<TrackerEntity>(TrackerTable)
}

object TrackerTable : IntIdTable("tracker") {

    val player: Column<EntityID<Int>> = reference("player_id", PlayersTable)
    val time_given: Column<Instant> = timestamp("time_given").clientDefault { Instant.now() }
    val location_given: Column<String> = text("location_given")
    val item_id: Column<Int> = integer("item_id")

}
