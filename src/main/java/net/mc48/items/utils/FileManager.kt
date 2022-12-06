package net.mc48.items.utils

import net.mc48.items.Items
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class FileManager(private val plugin: Items, private val fileName: String) {

    val config: YamlConfiguration
    private val file: File

    init {
        val dataFolder = plugin.dataFolder
        if (!dataFolder.exists()) dataFolder.mkdir()
        file = File(dataFolder, fileName)
        if (!file.exists()) plugin.saveResource(fileName, false)
        config = YamlConfiguration.loadConfiguration(file)
    }

    fun save() {
        if (!file.exists()) plugin.saveResource(fileName, false)
        config.save(file)
    }

    fun reload(executor: CommandSender): Boolean {
        return try {
            config.load(file)
            true
        } catch (exception: Exception) {
//            Messages.ROOT_ERROR.publish(executor)
            exception.printStackTrace()
            false
        }
    }
}