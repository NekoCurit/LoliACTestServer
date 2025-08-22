package net.nekocurit.loli_ac_server.plugin.command_trash

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.plugin.command_base.Command
import net.nekocurit.loli_ac_server.plugin.command_trash.ModCommandTrash.CONTAINER_TITLE
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryType

class CommandTrash: Command("trash", "清理垃圾") {

    override fun execute(player: EntityPlayer, args: List<String>) {
        player.bukkitEntity.openInventory(Bukkit.createInventory(null, InventoryType.CHEST, CONTAINER_TITLE))
    }

}