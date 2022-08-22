package me.coolkidwithu235.bordermanager

import org.bukkit.plugin.java.JavaPlugin
import me.coolkidwithu235.bordermanager.commands.Commands
import me.coolkidwithu235.bordermanager.events.Events

class BorderManager : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic

        logger.info("[INFO] Border Manager is: ENABLED")

        val eventObj = Events() // Defining one object, so it can be called in a different class like Commands()
        server.pluginManager.registerEvents(eventObj, this)
        this.getCommand("rlborders")?.setExecutor(Commands(eventObj))

    }

    override fun onDisable() {
        // Plugin shutdown logic

        logger.info("[INFO] Border Manager is: DISABLED")
    }
}