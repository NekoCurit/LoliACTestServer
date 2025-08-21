package net.nekocurit.loli_ac_server.plugin.zombie_protect.mixin;

import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityZombie.class)
public abstract class MixinEntityZombie {

    @Redirect(method = "m", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/v1_8_R3/World;w()Z"))
    public boolean tick$cancelFire(World instance) {
        return false;
    }

}
