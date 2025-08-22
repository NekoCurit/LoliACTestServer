package net.nekocurit.loli_ac_server.plugin.command_base

import net.minecraft.server.v1_8_R3.BlockPosition
import net.minecraft.server.v1_8_R3.EntityPlayer

/**
 * @param root 命令名称
 * @param args 当获取命令菜单时 这里是命令参数描述
 * @param description 当获取命令菜单时 这里是命令效果描述
 */
abstract class Command(val root: List<String>, val args: String, val description: String) {
    abstract fun execute(player: EntityPlayer, args: List<String>)
    open fun complete(player: EntityPlayer, args: List<String>, trigger: BlockPosition?): List<String> = listOf()
}