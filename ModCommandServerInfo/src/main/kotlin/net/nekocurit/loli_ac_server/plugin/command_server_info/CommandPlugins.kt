package net.nekocurit.loli_ac_server.plugin.command_server_info

import net.fabricmc.loader.api.FabricLoader
import net.minecraft.server.v1_8_R3.ChatComponentText
import net.minecraft.server.v1_8_R3.ChatHoverable
import net.minecraft.server.v1_8_R3.EntityPlayer
import net.minecraft.server.v1_8_R3.EnumChatFormat
import net.nekocurit.loli_ac_server.plugin.command_base.Command
import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase
import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase.sendCommandResponse
import net.nekocurit.loli_ac_server.utils.kotlin.intersperse
import org.bukkit.Bukkit

class CommandPlugins: Command(listOf("plugins"), "", "获取服务器插件列表") {

    companion object {
        val BLOCKED_PLUGIN_ID = arrayOf("")
    }

    override fun execute(player: EntityPlayer, args: List<String>) {
        ChatComponentText("Loli Plugins")
            .apply {
                FabricLoader.getInstance().allMods
                    .filter { !BLOCKED_PLUGIN_ID.contains(it.metadata.id) }
                    .also {
                        addSibling( ChatComponentText("(${it.size}): ").setNormalColor())
                    }
                    .map { mod ->
                        ChatComponentText(mod.metadata.id)
                            .apply {
                                chatModifier.color = EnumChatFormat.GREEN
                                chatModifier.setChatHoverable(ChatHoverable(
                                    ChatHoverable.EnumHoverAction.SHOW_TEXT,
                                    ChatComponentText("描述: ${mod.metadata.name ?: "这个入很懒, 什么描述都没写"}\n版本: ${mod.metadata.version.friendlyString}").setNormalColor()
                                ))
                            }
                    }
                    .intersperse {
                        ChatComponentText(", ").setNormalColor()
                    }
                    .forEach { addSibling(it) }
            }
            .also {
                player.sendMessage(it)
            }
        ChatComponentText("Bukkit Plugins")
            .apply {
                Bukkit.getPluginManager().plugins
                    .also {
                        addSibling( ChatComponentText("(${it.size}): ").setNormalColor())
                    }
                    .map { plugin ->
                        ChatComponentText(plugin.name)
                            .apply {
                                chatModifier.color = if (plugin.isEnabled) EnumChatFormat.GREEN else EnumChatFormat.RED
                                chatModifier.setChatHoverable(ChatHoverable(
                                    ChatHoverable.EnumHoverAction.SHOW_TEXT,
                                    ChatComponentText("描述: ${plugin.description.description ?: "这个入很懒, 什么描述都没写"}\n版本: ${plugin.description.version}").setNormalColor()
                                ))
                            }
                    }
                    .intersperse {
                        ChatComponentText(", ").setNormalColor()
                    }
                    .forEach { addSibling(it) }
            }
            .also {
                player.sendMessage(it)
            }



    }

    fun ChatComponentText.setNormalColor(): ChatComponentText {
        chatModifier.color = EnumChatFormat.WHITE
        return this
    }

}