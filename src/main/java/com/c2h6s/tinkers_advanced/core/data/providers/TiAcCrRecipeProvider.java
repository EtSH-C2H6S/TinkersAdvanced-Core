package com.c2h6s.tinkers_advanced.core.data.providers;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.init.TiAcCrItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.shared.TinkerCommons;

import java.util.function.Consumer;

public class TiAcCrRecipeProvider extends RecipeProvider implements ISmelteryRecipeHelper {
    public TiAcCrRecipeProvider(PackOutput generator) {
        super(generator);
    }
    public static final ResourceLocation baseFolder = new ResourceLocation(TinkersAdvanced.MODID,"core/");
    public static ResourceLocation namedFolder(String name){
        return ResourceLocation.tryParse(baseFolder+name+"/"+name);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        var folder = baseFolder;
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TiAcCrItem.ULTRA_DENSE_BOOK.get())
                .unlockedBy("has_item",has(Items.BOOK))
                .requires(Items.BOOK).requires(TinkerCommons.obsidianPane).requires(Tags.Items.DYES_PURPLE)
                .save(consumer,new ResourceLocation(folder+"ultra_dense_book"));
    }

    @Override
    public String getModId() {
        return TinkersAdvanced.MODID;
    }
}
