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


public class CompatConfigCondition implements ICondition {
    private final String modId;
    private final boolean isOriginal;
    public static final IConditionSerializer<CompatConfigCondition> SERIALIZER = new CompatSerializer();
    public static final ResourceLocation ID = TinkersAdvanced.getLocation("compat_enabled");

    public CompatConfigCondition(String modId, boolean isOriginal) {
        this.modId = modId;
        this.isOriginal = isOriginal;
    }

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(IContext context) {
        return isOriginal?
                (!TiAcCrConfig.COMMON.LIST_BLACKLIST_COMPAT_ORIGINAL.get().contains(modId)&&
                        TiAcCrConfig.COMMON.LIST_WHITELIST_COMPAT_ORIGINAL.get().isEmpty())||
                        TiAcCrConfig.COMMON.LIST_WHITELIST_COMPAT_ORIGINAL.get().contains(modId):
                (!TiAcCrConfig.COMMON.LIST_BLACKLIST_COMPAT.get().contains(modId)&&
                        TiAcCrConfig.COMMON.LIST_WHITELIST_COMPAT.get().isEmpty())||
                TiAcCrConfig.COMMON.LIST_WHITELIST_COMPAT.get().contains(modId);
    }
    private static class CompatSerializer implements Serializer<CompatConfigCondition>, IConditionSerializer<CompatConfigCondition> {
        @Override
        public ResourceLocation getID() {
            return CompatConfigCondition.ID;
        }

        @Override
        public void write(JsonObject json, CompatConfigCondition value) {
            json.addProperty("mod_id", value.modId);
            json.addProperty("is_original",value.isOriginal);
        }

        @Override
        public CompatConfigCondition read(JsonObject json) {
            String modId = GsonHelper.getAsString(json, "mod_id");
            boolean isOriginal = GsonHelper.getAsBoolean(json,"is_original");
            return new CompatConfigCondition(modId,isOriginal);
        }

        @Override
        public void serialize(JsonObject json, CompatConfigCondition condition, JsonSerializationContext context) {
            write(json, condition);
        }

        @Override
        public CompatConfigCondition deserialize(JsonObject json, JsonDeserializationContext context) {
            return read(json);
        }
    }
}
