package net.nekocurit.loli_ac_server.plugin.command_base

import net.minecraft.server.v1_8_R3.BlockPosition
import net.minecraft.server.v1_8_R3.EntityPlayer

abstract class Command(val root: String, val description: String) {
    abstract fun execute(player: EntityPlayer, args: List<String>)
    open fun complete(player: EntityPlayer, args: List<String>, trigger: BlockPosition?): List<String> = listOf()
}