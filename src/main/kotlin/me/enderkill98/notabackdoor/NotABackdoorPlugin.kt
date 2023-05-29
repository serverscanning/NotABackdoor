package me.enderkill98.notabackdoor

import me.enderkill98.notabackdoor.listeners.*
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class NotABackdoorPlugin: JavaPlugin() {

    companion object {
        @JvmStatic lateinit var INSTANCE: NotABackdoorPlugin
        const val PREFIX = "§6NotABackdoor §8§l| §f"
    }

    override fun onEnable() {
        INSTANCE = this
        // Register listeners
        val pm = Bukkit.getPluginManager()
        pm.registerEvents(RideCtaListener(), this)
    }

}