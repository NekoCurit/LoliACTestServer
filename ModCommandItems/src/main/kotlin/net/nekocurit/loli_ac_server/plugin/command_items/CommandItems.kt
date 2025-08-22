package net.nekocurit.loli_ac_server.plugin.command_items

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.plugin.command_base.Command
import net.nekocurit.loli_ac_server.plugin.command_items.ModCommandItems.CONTAINER_TITLE
import net.nekocurit.loli_ac_server.plugin.command_items.ModCommandItems.ITEMS
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryType

class CommandItems: Command("items", "快速获取物品") {

    override fun execute(player: EntityPlayer, args: List<String>) {
        Bukkit.createInventory(null, InventoryType.CHEST, CONTAINER_TITLE)
            .also { container ->
                ITEMS.forEach {
                    container.addItem(it)
                }
            }
            .also {
                player.bukkitEntity.openInventory(it)
            }
    }

}