
package com.github.gresh113.bionimine.datagen;

import com.github.gresh113.bionimine.Bionimine;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.LootTables;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Bionimine.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new RecipeGen((generator)));
           // generator.addProvider(new LootTables(generator));
        }
        /*if (event.includeClient()) {
            generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
        }*/
    }
}

