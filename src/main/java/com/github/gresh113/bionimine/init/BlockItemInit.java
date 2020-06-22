package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.Bionimine.BioniMineItemGroup;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockItemInit {
	// Block item Registry
	public static final DeferredRegister<Item> BLOCK_ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Bionimine.MODID);

	// Special Blocks
	public static final RegistryObject<Item> kanohi_pedestal = BLOCK_ITEMS.register("kanohi_pedestal", () -> new BlockItem(BlockInit.kanohi_pedestal.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	//@formatter:off
	public static final RegistryObject<Item> toa_crate_red = BLOCK_ITEMS.register("toa_crate_red", () -> 
		new BlockItem(BlockInit.toa_crate_red.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> toa_crate_blue = BLOCK_ITEMS.register("toa_crate_blue", () -> 
		new BlockItem(BlockInit.toa_crate_blue.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> toa_crate_green = BLOCK_ITEMS.register("toa_crate_green", () -> 
		new BlockItem(BlockInit.toa_crate_green.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> toa_crate_black = BLOCK_ITEMS.register("toa_crate_black", () -> 
		new BlockItem(BlockInit.toa_crate_black.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> toa_crate_white = BLOCK_ITEMS.register("toa_crate_white", () -> 
		new BlockItem(BlockInit.toa_crate_white.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> toa_crate_brown = BLOCK_ITEMS.register("toa_crate_brown", () -> 
		new BlockItem(BlockInit.toa_crate_brown.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	//@formatter:on

//	public static final RegistryObject<Item> flag = BLOCK_ITEMS.register("flag", () -> 
//		new BlockItem(BlockInit.kanohi_pedestal.get(), new Item.Properties().group(BioniMineItemGroup.instance)));

	// Building Blocks
	public static final RegistryObject<Item> protosteel_plating = BLOCK_ITEMS.register("protosteel_plating", () -> new BlockItem(BlockInit.protosteel_plating.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> matanui_stone = BLOCK_ITEMS.register("matanui_stone", () -> new BlockItem(BlockInit.matanui_stone.get(), new Item.Properties().group(BioniMineItemGroup.instance)));

	// Ores
	public static final RegistryObject<Item> lightstone_ore = BLOCK_ITEMS.register("lightstone_ore", () -> new BlockItem(BlockInit.lightstone_ore.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> protodermis_ore = BLOCK_ITEMS.register("protodermis_ore", () -> new BlockItem(BlockInit.protodermis_ore.get(), new Item.Properties().group(BioniMineItemGroup.instance)));

}
