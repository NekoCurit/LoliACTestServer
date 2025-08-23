package net.nekocurit.loli_ac_server.plugin.command_velocity

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase
import java.util.WeakHashMap

object ModCommandVelocity {

    val lockVelocityPlayers = WeakHashMap<EntityPlayer, LockVelocityInfo>()

    @JvmStatic
    fun onInitialize() {
        ModCommandBase.registerCommand(CommandVelocity())
    }

    @JvmStatic
    fun EntityPlayer.tick() {
        lockVelocityPlayers[this]?.also { info ->
            if (info.tick-- > 0) {
                motX = info.motionX
                motY = info.motionY
                motZ = info.motionZ
                velocityChanged = true
            } else {
                lockVelocityPlayers.remove(this)
            }
        }
    }

}