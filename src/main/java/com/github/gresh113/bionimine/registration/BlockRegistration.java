package com.github.gresh113.bionimine.registration;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.objects.blocks.FrostLayerBlock;
import com.github.gresh113.bionimine.objects.blocks.HauShieldBlock;
//import com.github.gresh113.bionimine.objects.blocks.KanohiPedestalBlock;
import com.github.gresh113.bionimine.objects.blocks.MataNuiBlock;
import com.github.gresh113.bionimine.objects.blocks.ToaCrateBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistration {

	// Blocks Registry
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Bionimine.MODID);
	
	public static final RegistryObject<Block> hau_shield = BLOCKS.register("hau_shield", () -> new HauShieldBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(10.0F, 10.0F).notSolid()));

	public static final RegistryObject<Block> frost = BLOCKS.register("frost", () -> new FrostLayerBlock(Block.Properties.create(Material.ICE).sound(SoundType.GLASS).notSolid()));
	
	// Functional BLocks
	//public static final RegistryObject<Block> kanohi_pedestal = BLOCKS.register("kanohi_pedestal", () -> new KanohiPedestalBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));

	private static final AbstractBlock.Properties toacrateproperties = Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F).sound(SoundType.STONE);
	public static final RegistryObject<Block> toa_crate_red = BLOCKS.register("toa_crate_red", () -> new ToaCrateBlock(toacrateproperties));
	public static final RegistryObject<Block> toa_crate_blue = BLOCKS.register("toa_crate_blue", () -> new ToaCrateBlock(toacrateproperties));
	public static final RegistryObject<Block> toa_crate_green = BLOCKS.register("toa_crate_green", () -> new ToaCrateBlock(toacrateproperties));
	public static final RegistryObject<Block> toa_crate_black = BLOCKS.register("toa_crate_black", () -> new ToaCrateBlock(toacrateproperties));
	public static final RegistryObject<Block> toa_crate_white = BLOCKS.register("toa_crate_white", () -> new ToaCrateBlock(toacrateproperties));
	public static final RegistryObject<Block> toa_crate_brown = BLOCKS.register("toa_crate_brown", () -> new ToaCrateBlock(toacrateproperties));

//	public static final RegistryObject<Block> flag = BLOCKS.register("flag", () ->
//		new FlagBlock(Block.Properties.create(Material.BAMBOO).hardnessAndResistance(3.0F, 3.0F)));

	// Building BLocks
	public static final RegistryObject<Block> protosteel_plating = BLOCKS.register("protosteel_plating", () -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5, 30).sound(SoundType.METAL)));

	public static final RegistryObject<Block> matanui_stone = BLOCKS.register("matanui_stone", () -> new MataNuiBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(5, 30).sound(SoundType.STONE)));

	// Ores
	public static final RegistryObject<Block> lightstone_ore = BLOCKS.register("lightstone_ore", () -> new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));
	public static final RegistryObject<Block> protodermis_ore = BLOCKS.register("protodermis_ore", () -> new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));

}
