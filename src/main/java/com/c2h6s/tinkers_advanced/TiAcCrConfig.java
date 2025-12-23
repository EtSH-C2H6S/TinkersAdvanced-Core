package com.c2h6s.tinkers_advanced;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Objects;

public class TiAcCrConfig {

    public static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }
    public static final ForgeConfigSpec clientSpec;
    public static final Client CLIENT;

    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        clientSpec = specPair.getRight();
        CLIENT = specPair.getLeft();
    }
    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TiAcCrConfig.commonSpec);
    }
    public static class Common{
        public final ForgeConfigSpec.BooleanValue HIDE_BANNED_MATERIAL;
        public final ForgeConfigSpec.BooleanValue ALLOW_ORIGINAL_MATERIALS;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> LIST_BLACKLIST_COMPAT;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> LIST_BLACKLIST_COMPAT_ORIGINAL;
//        public final ForgeConfigSpec.BooleanValue ALLOW_GIVE_BOOK;
        public final ForgeConfigSpec.BooleanValue ALLOW_AOE_ATTACK_PLAYER;

        public Common(ForgeConfigSpec.Builder builder){
            builder.comment("***注意！").comment("***Notice")
                    .comment("这些配置并不会同步修改语言文件中的描述，当你修改了某个工具/工具属性的性质后如果想要更改描述则需要手动覆盖语言文件！")
                    .comment("This configure won't affect language display. When a certain modifier or tool is configured, please manually replace the description of the relating language contents.");
            builder.comment("平衡性和游玩（施工中）").push("Gameplay (WIP)");
            HIDE_BANNED_MATERIAL = builder.comment("在JEI和创造模式物品栏中隐藏被被关闭的材料，默认true。")
                    .comment("Hide blacklisted materials, true by default")
                    .define("HideBlacklistedMaterials",true);
            ALLOW_ORIGINAL_MATERIALS = builder.comment("启用TiAc的原创材料，这些材料会有偏高的强度，默认true。")
                    .comment("这是一个总控，用于进行全局开关。如果你想精准控制各种联动材料的开关请使用IntegrationModBL或OriginalCompatModBL。")
                    .comment("Toggle original materials (Slightly OP), true by default.")
                    .define("AllowOriginalMaterials",true);
            LIST_BLACKLIST_COMPAT = builder.comment("集成型联动模组ID黑名单，向表中添加ModId来隐藏前沿匠艺对该模组的集成型联动。通用联动材料可通过tinkers_advanced这个id隐藏。")
                    .comment("黑名单模组对应的联动内容会被隐藏（不会加入创造模式物品栏，被JEI隐藏且配方不加载）。")
                    .comment("Integration ModId blacklist.")
                    .defineListAllowEmpty("IntegrationModBL", List.of("example_mod1","example_mod2"), Objects::nonNull);
            LIST_BLACKLIST_COMPAT_ORIGINAL = builder.comment("原创型联动模组ID黑名单，向表中添加ModId来隐藏前沿匠艺对该模组的原创型联动（比如热力的活化彩钢）。无联动材料可通过tinkers_advanced这个id隐藏。")
                    .comment("黑名单模组对应的联动内容会被隐藏（不会加入创造模式物品栏，被JEI隐藏且配方不加载）。")
                    .comment("Original Compat ModId blacklist.")
                    .defineListAllowEmpty("OriginalCompatModBL",List.of("example_mod1","example_mod2"), Objects::nonNull);
//            ALLOW_GIVE_BOOK = builder.comment("在玩家进入游戏时给予前沿匠艺的指导书，默认true。")
//                    .comment("Give player the Ultra Dense Book when spawn, true by default.")
//                    .define("GiveTheUltraDenseBook",true);
            ALLOW_AOE_ATTACK_PLAYER = builder.comment("允许绝大部分前沿匠艺的范围伤害打到玩家，不建议启用这个，默认false。")
                    .comment("Allow most of aoe damage from TiAc attacking player, false by default.")
                    .define("Allow Aoe Hit Players",false);
            builder.pop();
        }
    }
    public static class Client{
        public Client(ForgeConfigSpec.Builder builder){

        }
    }
}
