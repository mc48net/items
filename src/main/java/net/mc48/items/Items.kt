package net.mc48.items

import co.aikar.commands.PaperCommandManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.mc48.items.utils.FileManager
import net.mc48.items.utils.Messages
import org.bukkit.plugin.java.JavaPlugin

class Items : JavaPlugin() {

    lateinit var messageConfig: FileManager

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