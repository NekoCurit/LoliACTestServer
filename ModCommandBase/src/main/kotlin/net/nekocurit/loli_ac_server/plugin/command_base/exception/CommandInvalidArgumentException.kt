package net.nekocurit.loli_ac_server.plugin.command_base.exception

class CommandInvalidArgumentException(val arg: String, override val message: String): CommandExecuteException() {
    override fun getResponseMessage() = "无效的 $arg: $message"
}