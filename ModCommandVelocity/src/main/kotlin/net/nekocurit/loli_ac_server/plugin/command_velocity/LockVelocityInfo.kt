package net.nekocurit.loli_ac_server.plugin.command_velocity

data class LockVelocityInfo(
    var tick: Int,
    val motionX: Double,
    val motionY: Double,
    val motionZ: Double,
)