package me.coolkidwithu235.bordermanager.commands

import me.coolkidwithu235.bordermanager.events.Events
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

// Here the rlborders command is defined

class Commands(val eventHandler: Events) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if (sender is Player) {
            if (label == "rlborders") {

                eventHandler.updateBrdsFromFile()
                return true

            }
        }

        // Since the command should only be used by players, return false if it is used by something else
        return false
    }
}