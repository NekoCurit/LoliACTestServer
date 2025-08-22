package net.nekocurit.loli_ac_server.plugin.command_items.mixin;

import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.nekocurit.loli_ac_server.plugin.command_items.ModCommandItems;
import org.bukkit.craftbukkit.libs.joptsimple.OptionSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

    @Inject(method = "main", at = @At("HEAD"))
    private static void main$init(OptionSet options, CallbackInfo ci) {
        ModCommandItems.onInitialize();
    }

}
