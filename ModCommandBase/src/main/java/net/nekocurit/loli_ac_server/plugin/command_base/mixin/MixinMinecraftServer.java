package net.nekocurit.loli_ac_server.plugin.command_base.mixin;

import net.minecraft.server.v1_8_R3.*;
import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {

    @Inject(method = "tabCompleteCommand", at = @At("HEAD"), cancellable = true)
    private void tabCompleteCommand$inject(ICommandListener player, String s, BlockPosition trigger, CallbackInfoReturnable<List<String>> cir) {
        if (player instanceof EntityPlayer) {
            final List<String> completes = ModCommandBase.onTabComplete((EntityPlayer) player, s, trigger);
            if (completes != null) {
                cir.setReturnValue(completes);
                cir.cancel();
            }
        }
    }

}
