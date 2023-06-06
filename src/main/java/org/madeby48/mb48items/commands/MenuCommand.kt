package org.madeby48.mb48items.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player

@CommandAlias("items|mc48items|nbtitems")
@Description("Base command for the Main plugin.")
@CommandPermission("mc48.items")
class MenuCommand : BaseCommand() {

    @Default
    @CommandPermission("mc48.items.menu")
    fun default(sender: Player, args: Array<out String>) {
        sender.sendMessage(Component.text("This is a green coloured message", TextColor.color(20, 100, 20)))
    }

    @Subcommand("give")
    @CommandPermission("mc48.items.give")
    @Syntax("<player> <itemID>")
    fun giveItem(sender: Player, args: Array<out String>) {

        if (args.isEmpty() || args.size > 2) {
            sender.sendMessage(Component.text("You must supply the correct arguments for this command."))
            return
        }

        val player = Bukkit.getPlayer(args[0]) ?: run {
            sender.sendMessage(Component.text("The player you specified does not exist."))
            return
        }

        val itemID = args[1].toIntOrNull() ?: run {
            sender.sendMessage(Component.text("The item ID you specified is not a number."))
            return
        }



    }

    @Subcommand("printinfo")
    @CommandAlias("print|readinfo")
    @CommandPermission("mc48.items.print")
    fun printInfo(sender: Player, args: Array<out String>) {

    }

    /*
        This command will be used to fetch updated information from the database,
        for the item in the users hand
        and update the item in all player inventories.
     */
    @Subcommand("refreshitem")
    @CommandAlias("updateitem|replaceitem")
    @CommandPermission("mc48.items.replaceitem")
    fun updateItem(sender: Player, args: Array<out String>) {

    }

    /*
        This command will be used to fetch updated information from the database,
        for all items that exist,
        and update the item all player inventories.
     */
    @Subcommand("refreshallitems")
    @CommandAlias("updateallitems|replaceallitems")
    @CommandPermission("mc48.items.replaceallitems")
    fun updateAllItems(sender: Player, args: Array<out String>) {

    }
}