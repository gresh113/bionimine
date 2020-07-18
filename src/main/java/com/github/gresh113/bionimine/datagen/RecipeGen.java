package com.github.gresh113.bionimine.datagen;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.registration.ItemRegistration;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {
    public RecipeGen(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ItemRegistration.toa_stone.get())
                .patternLine(" p ")
                .patternLine("psp")
                .patternLine(" p ")
                .key('p', ItemRegistration.protodermis_ingot.get())
                .key('s', ItemRegistration.lightstone.get())
                .setGroup(Bionimine.MODID)
                .addCriterion("lightstone", InventoryChangeTrigger.Instance.forItems(ItemRegistration.lightstone.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemRegistration.bamboo_pole.get())
                .patternLine("  b")
                .patternLine(" b ")
                .patternLine("b  ")
                .key('b', Items.BAMBOO)
                .setGroup(Bionimine.MODID)
                .addCriterion("bamboo", InventoryChangeTrigger.Instance.forItems(Items.BAMBOO))
                .build(consumer);
    }


}
