package net.nekocurit.loli_ac_server.plugin.command_items

import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

object ModCommandItems {

    @JvmField
    val CONTAINER_TITLE = "${ChatColor.BLUE}获取物品"
    val ITEMS by lazy { arrayOf(
        ItemStack(Material.GLASS, 64), // 玻璃
        ItemStack(Material.LEAVES, 64), // 树叶
        ItemStack(Material.STONE, 64), // 石头
        ItemStack(Material.WOOL, 64, 0), // 羊毛 白色
        ItemStack(Material.WOOL, 64, 5), // 羊毛 淡绿色
        ItemStack(Material.WOOL, 64, 3), // 羊毛 淡蓝色
        ItemStack(Material.WOOL, 64, 14), // 羊毛 红色
        ItemStack(Material.WOOL, 64, 4), // 羊毛 黄色
        ItemStack(Material.WOOL, 64, 6), // 羊毛 粉色

        ItemStack(Material.DIAMOND_SWORD),
        ItemStack(Material.DIAMOND_PICKAXE),
        ItemStack(Material.DIAMOND_AXE),
        ItemStack(Material.DIAMOND_SPADE),
        ItemStack(Material.DIAMOND_HOE),
        ItemStack(Material.DIAMOND_HELMET),
        ItemStack(Material.DIAMOND_CHESTPLATE),
        ItemStack(Material.DIAMOND_LEGGINGS),
        ItemStack(Material.DIAMOND_BOOTS),

        ItemStack(Material.BOW), // 弓
        ItemStack(Material.ARROW), // 箭
        ItemStack(Material.FISHING_ROD), // 鱼竿
        ItemStack(Material.SHEARS), // 剪刀
        ItemStack(Material.STICK).apply { // 木棍 - 击退2
            itemMeta = itemMeta?.apply {
                addEnchant(Enchantment.KNOCKBACK, 2, true)
            }
        },
        ItemStack(Material.GOLDEN_APPLE, 64, 0), // 金苹果 - 普通
        ItemStack(Material.GOLDEN_APPLE, 64, 1), // 金苹果 - 附魔
        ItemStack(Material.WATER_BUCKET), // 水桶
        ItemStack(Material.ENDER_PEARL, 64) // 末影珍珠
    ) }

    @JvmStatic
    fun onInitialize() {
        ModCommandBase.registerCommand(CommandItems())
    }

    @JvmStatic
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.view.title == CONTAINER_TITLE) {
            if (event.clickedInventory != event.whoClicked.inventory) {
                event.whoClicked.inventory.addItem(event.currentItem ?: return) // 给予玩家物品
            }
            event.isCancelled = true
        }
    }

}