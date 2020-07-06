package com.github.gresh113.bionimine.world.gen;

import com.github.gresh113.bionimine.init.BlockRegistration;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ProtodermisOreGen {
	public static void generateOre() {
		final int max_height = 60;
		final int min_height = 5;
		final int rarity = 15;
		final FillerBlockType fillerBlock = OreFeatureConfig.FillerBlockType.NATURAL_STONE;
		
		for (Biome biome : ForgeRegistries.BIOMES) {
			ConfiguredPlacement<CountRangeConfig> protodermisConfig = Placement.COUNT_RANGE
					.configure(new CountRangeConfig(rarity, min_height, 0, max_height));
			
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE
							.withConfiguration(new OreFeatureConfig(fillerBlock,
									BlockRegistration.protodermis_ore.get().getDefaultState(), 10))
							.withPlacement(protodermisConfig));
		}
	}
}
