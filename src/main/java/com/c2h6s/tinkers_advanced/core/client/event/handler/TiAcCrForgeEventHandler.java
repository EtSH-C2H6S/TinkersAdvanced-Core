package com.c2h6s.tinkers_advanced.core.client.event.handler;

import com.c2h6s.tinkers_advanced.core.init.TiAcCrModifiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderBlockScreenEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
public class TiAcCrForgeEventHandler {
    @SubscribeEvent
    public static void onRenderBlockOverlay(RenderBlockScreenEffectEvent event){
        var player = event.getPlayer();
        var stack = player.getItemBySlot(EquipmentSlot.HEAD);
        if (stack.getItem() instanceof IModifiable){
            var tool = ToolStack.from(stack);
            if (tool.getModifierLevel(TiAcCrModifiers.PLASMA_PROOF_GOGGLES.get())>0) event.setCanceled(true);
        }
    }
}
