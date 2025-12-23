package com.c2h6s.tinkers_advanced.core.util;

import com.c2h6s.etstlib.util.IToolUuidGetter;
import com.c2h6s.etstlib.util.ModListConstants;
import com.c2h6s.etstlib.util.UUIDUtil;
import com.c2h6s.tinkers_advanced.TiAcCrConfig;
import com.c2h6s.tinkers_advanced.core.content.entity.VisualScaledProjectile;
import com.c2h6s.tinkers_advanced.core.library.compact.pnc.capability.ItemMachineConvertHandler;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

import static com.c2h6s.etstlib.util.EntityInRangeUtil.toManhattanDistance;

public class CommonUtil {
    public static ArmorItem.Type[] ALL_ARMOR = new ArmorItem.Type[]{ArmorItem.Type.HELMET, ArmorItem.Type.CHESTPLATE, ArmorItem.Type.LEGGINGS, ArmorItem.Type.BOOTS};

    public static String KEY_ATTACKER = "tinkers_advanced_attacker";

    public static Entity getNearestEntity(@NotNull Entity centerEntity, float range, @NotNull IntOpenHashSet ignoreEntityIds, @NotNull Predicate<Entity> predicate){
        List<Entity> list = centerEntity.level().getEntitiesOfClass(Entity.class,new AABB(centerEntity.blockPosition()).inflate(range));
        list.sort(Comparator.comparingDouble(toManhattanDistance(centerEntity)));
        for (Entity entity:list){
            if (!ignoreEntityIds.contains(entity.getId())&&predicate.test(entity)&&entity!=centerEntity){
                return entity;
            }
        }
        return null;
    }

    public static String getUnitLong(long amount){
        int a = (int) Math.log10(amount);
        int b =a/3;
        switch (b){
            case 1->{
                return String.format("%.2f",(float)amount/1E+3)+" k";
            }
            case 2->{
                return String.format("%.2f",(float)amount/1E+6)+" M";
            }
            case 3->{
                return String.format("%.2f",(float)amount/1E+9)+" G";
            }
            case 4->{
                return String.format("%.2f",(float)amount/1E+12)+" T";
            }
            case 5->{
                return String.format("%.2f",(float)amount/1E+15)+" P";
            }
            case 6->{
                return String.format("%.2f",(float)amount/1E+18)+" E";
            }
            default-> {
                return amount + " ";
            }
        }
    }

    public static String getEnergyString(long amount){
        return getUnitLong(amount)+"FE";
    }

    public static <T> @NotNull LazyOptional<T> getCompactCapability(@NotNull ItemStack stack, @NotNull Capability<T> capability, @Nullable Direction direction){
        if (ModListConstants.PnCLoaded){
            ItemMachineConvertHandler handler = new ItemMachineConvertHandler(stack);
            if (handler.getCapability(capability).isPresent()) return handler.getCapability(capability).cast();
        }
        return LazyOptional.empty();
    }

    public static boolean isModifiable(ItemStack stack){
        return stack.getItem() instanceof IModifiable;
    }

    public static @NotNull UUID getUUIDFromTool(IToolStackView tool, ModifierId modifierId, EquipmentSlot slot){
        return IToolUuidGetter.getUuidForTool(tool).isPresent()?IToolUuidGetter.getUuidForTool(tool).get(): UUIDUtil.UUIDFromSlot(slot,modifierId);
    }

    public static boolean checkTarget(Projectile projectile , Entity target){
        return checkTarget(projectile.getOwner() instanceof LivingEntity living?living:null,target);
    }
    public static boolean checkTarget(@Nullable LivingEntity attacker , Entity target){
        if (!TiAcCrConfig.COMMON.ALLOW_AOE_ATTACK_PLAYER.get()&&target instanceof Player) return false;
        if (target==attacker) return false;
        if (target instanceof VisualScaledProjectile||target instanceof ItemEntity||target instanceof ExperienceOrb)
            return false;
        if (attacker instanceof Player player&&target instanceof Player player1) return player.canHarmPlayer(player1);

        return attacker == null || !target.isAlliedTo(attacker);
    }


    public static int processConsumptionInt(int consumption,float efficiency){
        efficiency = Math.min(efficiency,1);
        float mul = 1-efficiency;
        float f = consumption*mul;
        int i = (int) f;
        f-=i;
        Random random = new Random();
        return i + (random.nextFloat()<f?1:0);
    }
}
