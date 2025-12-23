package com.c2h6s.tinkers_advanced.mixin.client;

import com.c2h6s.tinkers_advanced.core.init.TiAcCrModifiers;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow public abstract Entity getEntity();

    @Inject(method = "getFluidInCamera",at = @At("HEAD"),cancellable = true)
    private void resetFluidFog(CallbackInfoReturnable<FogType> cir){
        var entity = getEntity();
        if (entity instanceof LivingEntity living){
            var stack = living.getItemBySlot(EquipmentSlot.HEAD);
            if (stack.getItem() instanceof IModifiable){
                var tool = ToolStack.from(stack);
                if (tool.getModifierLevel(TiAcCrModifiers.PLASMA_PROOF_GOGGLES.get())>0) cir.setReturnValue(FogType.NONE);
            }
        }
    }
}
