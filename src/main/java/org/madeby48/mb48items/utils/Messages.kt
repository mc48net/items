package org.madeby48.mb48items.utils

import net.kyori.adventure.audience.Audience
import org.madeby48.mb48items.Main
import org.madeby48.mb48items.Main.Companion.translateColour
import java.util.logging.Level

enum class Messages(val priority: Level, val defaultInput: String, val path: String) {

    ROOT_HELLO(Level.INFO, "Hello, world.", "root.hello"),
    ROOT_FATAL(Level.SEVERE, "A fatal error has occurred.", "root.fatal"),
    ROOT_ERROR(Level.WARNING, "An error has occurred, but is not fatal.", "root.error"),

    ROOT_DEBUG(Level.INFO, "Debug: %s", "root.debug");

    fun publish(plugin: Main, reciever: Audience, vararg args: Any?){

        var message = plugin.messageConfig.config.getString(path, defaultInput)

//        message = when (this) {
//            TELEPORT_SUCCESS_OTHERS -> message.replace("{0}", args[0].toString()).replace("{1}", args[1].toString())
//            else -> if (args.isNotEmpty()) message.replace("%s", args[0].toString()) else message
//        }

        message?.let { translateColour(it) }?.let { reciever.sendMessage(it) }
    }

}