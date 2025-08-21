@file:Suppress("NOTHING_TO_INLINE")

package net.nekocurit.loli_ac_server.utils

import net.minecraft.server.v1_8_R3.ChatComponentText
import net.minecraft.server.v1_8_R3.EntityPlayer
import net.minecraft.server.v1_8_R3.PacketPlayOutChat

inline fun EntityPlayer.sendActionBar(message: String) {
    playerConnection.sendPacket(PacketPlayOutChat(ChatComponentText(message), 2))
}