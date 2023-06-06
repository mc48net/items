package org.madeby48.mb48items

import co.aikar.commands.PaperCommandManager
import org.madeby48.mb48items.config.Configuration
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.madeby48.mb48items.utils.FileManager
import org.madeby48.mb48items.utils.Messages
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.Database

/**
 * The Main class of the plugin.
 * Authors: madebydylan, BomBardyGamer
 * Code has been graciously borrowed from the ModRequests plugin by BomBardyGamer.
 * Props to him for the original code of the entire configuration logic, and database thingamajigs.
 */

class Main : JavaPlugin() {

    lateinit var messageConfig: FileManager


    @PublishedApi
    internal var database: Database? = null
    private var internalConfig: Configuration? = null
    val configuration: Configuration
        get() = checkNotNull(internalConfig) { "Configuration was not initialized properly!" }
    val itemsManager: ItemsManager = ItemsManager(this)
    val playerManager: PlayerManager = PlayerManager(this)

    inline fun <T> transaction(crossinline block: () -> T): T =
        org.jetbrains.exposed.sql.transactions.transaction(database!!) { block() }

    override fun onEnable() {

        val manager = PaperCommandManager(this)
        messageConfig = FileManager(this, "messages.yml")

        // Plugin startup logic
    }

    override fun onDisable() {
        // Plugin shutdown logic


    }

    /** Function that loads / and creates the message config file. */
    private fun setupMessageConfig() {
        Messages.values().forEach {
            if (!messageConfig.config.isString(it.path)) messageConfig.config.set(it.path, it.defaultInput)
        }
        messageConfig.save()
    }

    companion object {

        /**
         * Function that returns a deserialized coloured component.
         * @param input The string to translate.
         */
        fun translateColour(input: String): Component = LegacyComponentSerializer.legacyAmpersand().deserialize(input)
    }
}