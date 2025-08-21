package net.nekocurit.loli_ac_server.plugin.demo.mixin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LogManager.class)
public abstract class MainMixin {

    @Shadow
    public static Logger getLogger(Class<?> clazz) {
        throw new RuntimeException("");
    }

    @Inject(method = "getLogger()Lorg/apache/logging/log4j/Logger;", at = @At("HEAD"), cancellable = true)
    private static void injectGetGreeting(CallbackInfoReturnable<Logger> cir) {
        System.out.println("LLLLLLLL");

        cir.setReturnValue(getLogger(Object.class));
        cir.cancel();
    }

}
