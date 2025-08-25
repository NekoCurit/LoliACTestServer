package net.nekocurit.loli_ac_server.plugin.grass_block_keeper.mixin;

import net.minecraft.server.v1_8_R3.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockGrass.class)
public class MixinBlockGrass {


    @Inject(
            method = "b(Lnet/minecraft/server/v1_8_R3/World;Lnet/minecraft/server/v1_8_R3/BlockPosition;Lnet/minecraft/server/v1_8_R3/IBlockData;Ljava/util/Random;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void update$cancel(World world, BlockPosition blockposition, IBlockData iblockdata, Random random, CallbackInfo ci) {
        ci.cancel();
    }

}
