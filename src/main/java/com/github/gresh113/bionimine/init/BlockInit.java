package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.objects.blocks.KanohiPedestalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	//Blocks Registry
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, BioniMine.MODID);
	
	public static final RegistryObject<Block> kanohi_pedestal = BLOCKS.register("kanohi_pedestal", () ->
		new KanohiPedestalBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));
	public static final RegistryObject<Block> protosteel_plating = BLOCKS.register("protosteel_plating", () ->
		new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5, 30).sound(SoundType.METAL)));
	public static final RegistryObject<Block> lightstone_ore = BLOCKS.register("lightstone_ore", () ->
		new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));
	
	
	
	
}
