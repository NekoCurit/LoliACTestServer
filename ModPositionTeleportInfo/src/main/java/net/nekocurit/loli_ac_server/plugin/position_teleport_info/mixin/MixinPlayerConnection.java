package net.nekocurit.loli_ac_server.plugin.position_teleport_info.mixin;

import net.minecraft.server.v1_8_R3.*;
import net.nekocurit.loli_ac_server.plugin.position_teleport_info.PositionData;
import net.nekocurit.loli_ac_server.plugin.position_teleport_info.ModPositionTeleportInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerConnection.class)
public abstract class MixinPlayerConnection {

    @Shadow public EntityPlayer player;

    @Inject(method = "sendPacket", at = @At("HEAD"))
    private void injectGetGreeting(Packet base, CallbackInfo ci) {
        if (base instanceof AccessorPacketPlayOutPosition) {
            final AccessorPacketPlayOutPosition packet = (AccessorPacketPlayOutPosition) base;
            ModPositionTeleportInfo.lastPositions.put(this.player, new PositionData(System.currentTimeMillis(), packet.getX(), packet.getY(), packet.getZ()));
        }
    }

}
