package net.nekocurit.loli_ac_server.plugin.zombie_protect.mixin;

import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityZombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLiving.class)
public abstract class MixinEntityLiving {

    @Inject(method = "d(Lnet/minecraft/server/v1_8_R3/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
    public void damage$cancelZombie(DamageSource damagesource, float f, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof EntityZombie) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

}
