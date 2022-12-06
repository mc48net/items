package net.mc48.items.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.entity.Player

@CommandAlias("items|mc48items|nbtitems")
@Description("Base command for the Items plugin.")
@CommandPermission("mc48.items")
class MenuCommand : BaseCommand() {

    @Default
    @CommandPermission("mc48.items.menu")
    fun default(sender: Player, args: Array<out String>) {
        sender.sendMessage(Component.text("This is a green coloured message", TextColor.color(20, 100, 20)))
    }

    @Subcommand("give")
    @CommandPermission("mc48.items.give")
    fun giveItem(sender: Player, args: Array<out String>) {

    }

    @Subcommand("printinfo")
    @CommandAlias("print|readinfo")
    @CommandPermission("mc48.items.print")
    fun printInfo(sender: Player, args: Array<out String>) {

    }

    @Subcommand("refreshitem")
    @CommandAlias("updateitem|replaceitem")
    @CommandPermission("mc48.items.replaceitem")
    fun updateItem(sender: Player, args: Array<out String>) {

    }

    @Subcommand("refreshconnection")
    @CommandAlias("refcon")
    @CommandPermission("mc48.items.admin")
    fun refreshConnection(sender: Player, args: Array<out String>) {

    }
}