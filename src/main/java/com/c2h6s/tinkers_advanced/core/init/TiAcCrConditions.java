package com.c2h6s.tinkers_advanced.core.init;

import com.c2h6s.tinkers_advanced.TiAcCrConfig;
import com.c2h6s.tinkers_advanced.core.data.condition.ConfigCondition;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TiAcCrConditions {
    public static void init(){
        ConfigCondition.add(ALLOW_ORIGINAL_MATERIALS);
    }
    public static final ConfigCondition ALLOW_ORIGINAL_MATERIALS = new ConfigCondition("allow_original_materials", TiAcCrConfig.COMMON.ALLOW_ORIGINAL_MATERIALS);
}
