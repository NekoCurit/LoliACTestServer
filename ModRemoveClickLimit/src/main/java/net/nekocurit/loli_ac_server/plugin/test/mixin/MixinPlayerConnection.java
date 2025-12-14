package net.nekocurit.loli_ac_server.plugin.test.mixin;

import net.minecraft.server.v1_8_R3.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerConnection.class)
public class MixinPlayerConnection {

    @Redirect(method = "a(Lnet/minecraft/server/v1_8_R3/PacketPlayInWindowClick;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/v1_8_R3/Container;a(Lnet/minecraft/server/v1_8_R3/EntityHuman;Z)V"))
    public void handlePacketPlayInWindowClickX(Container instance, EntityHuman entityhuman, boolean flag) {

    }

}
