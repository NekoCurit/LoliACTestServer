package net.nekocurit.loli_ac_server.plugin.scoreboard.mixin;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.v1_8_R3.*;
import net.nekocurit.loli_ac_server.plugin.scoreboard.ModScoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public abstract class MixinNetworkManager {

    @Shadow private PacketListener m;

    @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/server/v1_8_R3/Packet;)V", at = @At("HEAD"))
    private void channelRead0$log(ChannelHandlerContext channelhandlercontext, Packet object, CallbackInfo ci) {
        if (this.m instanceof PlayerConnection) {
            final PlayerConnection connection = (PlayerConnection) this.m;

            ModScoreboard.multiStatisticsManager.getPlayerDataOrCreate(connection.player).getPpsOut().add();
            if (object instanceof PacketPlayInArmAnimation) ModScoreboard.multiStatisticsManager.getPlayerDataOrCreate(connection.player).getCps().add();
        }
    }

}
