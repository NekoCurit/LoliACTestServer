package net.nekocurit.loli_ac_server.plugin.scoreboard.mixin;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.nekocurit.loli_ac_server.plugin.scoreboard.ModScoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerConnection.class)
public abstract class MixinPlayerConnection {

    @Shadow
    public EntityPlayer player;

    @Inject(method = "sendPacket", at = @At("HEAD"))
    private void injectGetGreeting(Packet packet, CallbackInfo ci) {
        ModScoreboard.multiStatisticsManager.getPlayerDataOrCreate(this.player).getPpsIn().add();
    }

}
