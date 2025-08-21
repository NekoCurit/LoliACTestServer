package net.nekocurit.loli_ac_server.plugin.position_teleport_info.mixin;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.nekocurit.loli_ac_server.plugin.position_teleport_info.PositionTeleportInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {

    @Inject(method = "t_", at = @At("HEAD"))
    private void tick$pre(CallbackInfo ci) {
        PositionTeleportInfo.tick((EntityPlayer)(Object)this);
    }

}
