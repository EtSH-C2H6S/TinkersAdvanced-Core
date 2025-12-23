package com.c2h6s.tinkers_advanced.core.content.tool.tinkering.materialStat;

import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

public class FluxCoreMaterialStatId extends MaterialStatsId {
    public FluxCoreMaterialStatId(ResourceLocation location) {
        super(location);
    }

    @Override
    public boolean canUseMaterial(MaterialId material) {
        return super.canUseMaterial(material)|| StatlessMaterialStats.BINDING.getIdentifier().canUseMaterial(material);
    }
}
