package net.nekocurit.loli_ac_server.plugin.command_items.mixin;

import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.nekocurit.loli_ac_server.plugin.command_items.ModCommandItems;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.PluginManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerConnection.class)
public class MixinPlayerConnection {

    @Redirect(method = "a(Lnet/minecraft/server/v1_8_R3/PacketPlayInWindowClick;)V", at = @At(value = "INVOKE", target = "Lorg/bukkit/plugin/PluginManager;callEvent(Lorg/bukkit/event/Event;)V"))
    public void handlePacketPlayInWindowClick$init(PluginManager instance, Event event) {
        ModCommandItems.onInventoryClick((InventoryClickEvent) event);
        instance.callEvent(event);
    }

}
