package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.BioniMine.BioniMineItemGroup;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockItemInit {
	//Block item Registry
	public static final DeferredRegister<Item> BLOCK_ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, BioniMine.MODID);
	
	//Special Blocks
	public static final RegistryObject<Item> kanohi_pedestal = BLOCK_ITEMS.register("kanohi_pedestal", () -> 
		new BlockItem(BlockInit.kanohi_pedestal.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	
	//Building Blocks
	public static final RegistryObject<Item> protosteel_plating = BLOCK_ITEMS.register("protosteel_plating", () -> 
		new BlockItem(BlockInit.protosteel_plating.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	
	//Ores
	public static final RegistryObject<Item> lightstone_ore = BLOCK_ITEMS.register("lightstone_ore", () -> 
		new BlockItem(BlockInit.lightstone_ore.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> protodermis_ore = BLOCK_ITEMS.register("protodermis_ore", () -> 
		new BlockItem(BlockInit.protodermis_ore.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	
}
