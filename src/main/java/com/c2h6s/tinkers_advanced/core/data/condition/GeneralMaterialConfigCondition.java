package com.c2h6s.tinkers_advanced.core.data.condition;

import com.c2h6s.tinkers_advanced.TiAcCrConfig;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;


public class GeneralMaterialConfigCondition implements ICondition {
    private final String name;
    public static final IConditionSerializer<GeneralMaterialConfigCondition> SERIALIZER = new CompatSerializer();
    public static final ResourceLocation ID = TinkersAdvanced.getLocation("general_material_enabled");

    public GeneralMaterialConfigCondition(String name) {
        this.name = name;
    }

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(IContext context) {
        return (!TiAcCrConfig.COMMON.LIST_BLACKLIST_GENERAL.get().contains(name)&&TiAcCrConfig.COMMON.LIST_WHITELIST_GENERAL.get().isEmpty())||
                TiAcCrConfig.COMMON.LIST_WHITELIST_GENERAL.get().contains(name);
    }
    private static class CompatSerializer implements Serializer<GeneralMaterialConfigCondition>, IConditionSerializer<GeneralMaterialConfigCondition> {
        @Override
        public ResourceLocation getID() {
            return GeneralMaterialConfigCondition.ID;
        }

        @Override
        public void write(JsonObject json, GeneralMaterialConfigCondition value) {
            json.addProperty("name", value.name);
        }

        @Override
        public GeneralMaterialConfigCondition read(JsonObject json) {
            String modId = GsonHelper.getAsString(json, "name");
            return new GeneralMaterialConfigCondition(modId);
        }

        @Override
        public void serialize(JsonObject json, GeneralMaterialConfigCondition condition, JsonSerializationContext context) {
            write(json, condition);
        }

        @Override
        public GeneralMaterialConfigCondition deserialize(JsonObject json, JsonDeserializationContext context) {
            return read(json);
        }
    }
}
