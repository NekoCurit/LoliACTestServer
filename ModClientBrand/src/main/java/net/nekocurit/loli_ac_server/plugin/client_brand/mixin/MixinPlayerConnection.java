package net.nekocurit.loli_ac_server.plugin.client_brand.mixin;

import com.google.common.base.Charsets;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.nekocurit.loli_ac_server.plugin.client_brand.ModClientBrand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerConnection.class)
public class MixinPlayerConnection {

    @Shadow
    public EntityPlayer player;

    @Inject(method = "a(Lnet/minecraft/server/v1_8_R3/PacketPlayInCustomPayload;)V", at = @At("HEAD"))
    public void handlePacketPlayInCustomPayload$init(PacketPlayInCustomPayload packet, CallbackInfo ci) {
        if (packet.a().equals("MC|Brand")) {
            ModClientBrand.sendClientBrand(player,  packet.b().toString(Charsets.UTF_8));
        }
    }

}
