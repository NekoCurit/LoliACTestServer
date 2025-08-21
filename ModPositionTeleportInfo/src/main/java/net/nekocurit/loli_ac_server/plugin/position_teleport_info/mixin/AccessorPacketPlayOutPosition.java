package net.nekocurit.loli_ac_server.plugin.position_teleport_info.mixin;

import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PacketPlayOutPosition.class)
public interface AccessorPacketPlayOutPosition {
    
    @Accessor("a")
    double getX();
    
    @Accessor("b")
    double getY();
    
    @Accessor("c")
    double getZ();

}
