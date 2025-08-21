package net.nekocurit.loli_ac_server.plugin.use_progress.mixin;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.nekocurit.loli_ac_server.plugin.use_progress.ModUseProgress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityHuman.class)
public abstract class MixinEntityHuman {

    @Unique
    public boolean loli$lastState = false;

    @Shadow
    private ItemStack g;

    @Shadow
    private int h;

    @Inject(method = "t_", at = @At("HEAD"))
    private void tick$pre(CallbackInfo ci) {
        if ((Object) this instanceof EntityPlayer) {
            if (this.g == null) {
                if (loli$lastState) {
                    ModUseProgress.sendActionbarClear((EntityPlayer)(Object) this);
                    loli$lastState = false;
                }
                return;
            }

            ModUseProgress.sendActionbar((EntityPlayer)(Object) this, this.g, this.h);
            loli$lastState = true;
        }

    }

}
