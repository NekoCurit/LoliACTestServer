package net.nekocurit.loli_ac_server.plugin.zombie_protect.mixin;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityZombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity {

    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    public void move$cancelZombie(CallbackInfo ci) {
        if ((Object) this instanceof EntityZombie) ci.cancel();
    }

}
