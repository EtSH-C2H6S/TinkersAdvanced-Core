package com.c2h6s.tinkers_advanced.core.init;

import com.c2h6s.tinkers_advanced.core.TiAcCrModule;
import com.c2h6s.tinkers_advanced.core.content.event.TiAcLoadRegistryClassEvent;
import com.c2h6s.tinkers_advanced.core.content.tool.modifiers.AutoShoot;
import com.c2h6s.tinkers_advanced.core.content.tool.modifiers.PlasmaProofGoggles;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TiAcCrModifiers {
    @SubscribeEvent
    public static void init(TiAcLoadRegistryClassEvent event){}

    public static final StaticModifier<PlasmaProofGoggles> PLASMA_PROOF_GOGGLES = TiAcCrModule.MODIFIERS.register("plasma_proof_goggles",PlasmaProofGoggles::new);
    public static final StaticModifier<AutoShoot> AUTO_SHOOT = TiAcCrModule.MODIFIERS.register("auto_shoot",AutoShoot::new);
}
