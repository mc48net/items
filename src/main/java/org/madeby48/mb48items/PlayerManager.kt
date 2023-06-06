package org.madeby48.mb48items

import org.madeby48.mb48items.db.PlayerEntity
import org.madeby48.mb48items.db.PlayersTable
import java.util.UUID
import org.bukkit.entity.Player as BukkitPlayer

class PlayerManager(private val plugin: Main) {

    fun getById(id: UUID): PlayerEntity? = plugin.transaction { findById(id) }

    fun getOrCreate(player: BukkitPlayer): PlayerEntity = plugin.transaction { findById(player.uniqueId) ?: create(player) }

    private fun findById(id: UUID): PlayerEntity? = PlayerEntity.find { PlayersTable.uuid eq id }.firstOrNull()

    private fun create(player: BukkitPlayer): PlayerEntity = PlayerEntity.new {
        uuid = player.uniqueId
        name = player.name
    }
}