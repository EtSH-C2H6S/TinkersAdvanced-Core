package com.c2h6s.tinkers_advanced.core.init;

import com.c2h6s.tinkers_advanced.core.content.tool.tinkering.materialStat.*;
import slimeknights.tconstruct.library.materials.IMaterialRegistry;
import slimeknights.tconstruct.library.materials.MaterialRegistry;

public class TiAcCrMaterialStat {
    public static void init(){
        IMaterialRegistry registry = MaterialRegistry.getInstance();
        registry.registerStatType(FluxCoreMaterialStat.TYPE, MaterialRegistry.MELEE_HARVEST);
    }
}
