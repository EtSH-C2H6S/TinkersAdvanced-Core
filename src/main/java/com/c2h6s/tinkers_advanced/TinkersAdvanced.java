package com.c2h6s.tinkers_advanced;

import com.c2h6s.tinkers_advanced.core.TiAcCrConfig;
import com.c2h6s.tinkers_advanced.core.TiAcCrModule;
import com.c2h6s.tinkers_advanced.core.content.entity.VisualScaledProjectile;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.tools.capability.EntityModifierCapability;

import java.util.Random;

@Mod(TinkersAdvanced.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersAdvanced
{
    public static final String MODID = "tinkers_advanced";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static Random RANDOM = new Random();
    public static ResourceLocation getLocation(String name){return new ResourceLocation(MODID,name);}


    public TinkersAdvanced()
    {
        FMLJavaModLoadingContext context = FMLJavaModLoadingContext.get();
        IEventBus modEventBus = context.getModEventBus();

        TiAcCrConfig.init();
        TiAcCrModule.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    static void commonSetup(final FMLCommonSetupEvent event)
    {
        EntityModifierCapability.registerEntityPredicate(entity -> entity instanceof VisualScaledProjectile);
    }

}
