package net.nekocurit.loli_ac_server.plugin.command_base.mixin;

import net.minecraft.server.v1_8_R3.*;
import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerConnection.class)
public abstract class MixinPlayerConnection {

    @Shadow
    public EntityPlayer player;

    @Inject(method = "handleCommand", at = @At("HEAD"), cancellable = true)
    private void handleCommand$inject(String s, CallbackInfo ci) {
        if (ModCommandBase.onCommand(player, s)) {
            ci.cancel();
        }
    }

}
