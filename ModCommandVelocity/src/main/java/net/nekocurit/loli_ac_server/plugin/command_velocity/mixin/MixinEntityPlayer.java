package net.nekocurit.loli_ac_server.plugin.command_velocity.mixin;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.nekocurit.loli_ac_server.plugin.command_velocity.ModCommandVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {

    @Inject(method = "t_", at = @At("HEAD"))
    private void tick$pre(CallbackInfo ci) {
        ModCommandVelocity.tick((EntityPlayer)(Object)this);
    }

}
