package net.nekocurit.loli_ac_server.plugin.command_velocity

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.plugin.command_base.Command
import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase.sendCommandResponse
import net.nekocurit.loli_ac_server.plugin.command_base.exception.CommandInvalidArgumentException

class CommandVelocity: Command(listOf("velocity"), "<MotionX> <MotionY> <MotionZ> [Tick]", "设置你的击退动量") {

    override fun execute(player: EntityPlayer, args: List<String>) {
        val motionX = args.getOrNull(0)?.toDoubleOrNull() ?: throw CommandInvalidArgumentException("MotionX",  "合法范围 -5 ~ 5")
        val motionY = args.getOrNull(1)?.toDoubleOrNull() ?: throw CommandInvalidArgumentException("MotionY",  "合法范围 -5 ~ 5")
        val motionZ = args.getOrNull(2)?.toDoubleOrNull() ?: throw CommandInvalidArgumentException("MotionZ",  "合法范围 -5 ~ 5")
        val tick = args.getOrNull(3)?.toIntOrNull()?.coerceAtLeast(1) ?: 1

        ModCommandVelocity.lockVelocityPlayers[player] = LockVelocityInfo(tick, motionX, motionY, motionZ)
        player.sendCommandResponse("设置成功")
    }

}