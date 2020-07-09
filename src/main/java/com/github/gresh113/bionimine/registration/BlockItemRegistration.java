package com.github.gresh113.bionimine.registration;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.Bionimine.BioniMineItemGroup;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockItemRegistration {
	// Block item Registry
	public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Bionimine.MODID);

	// Special Blocks
	//public static final RegistryObject<Item> kanohi_pedestal = BLOCK_ITEMS.register("kanohi_pedestal", () -> new BlockItem(BlockInit.kanohi_pedestal.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	//@formatter:off
	public static final RegistryObject<Item> toa_crate_red = BLOCK_ITEMS.register("toa_crate_red", () -> 
		new BlockItem(BlockRegistration.toa_crate_red.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> toa_crate_blue = BLOCK_ITEMS.register("toa_crate_blue", () -> 
		new BlockItem(BlockRegistration.toa_crate_blue.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> toa_crate_green = BLOCK_ITEMS.register("toa_crate_green", () -> 
		new BlockItem(BlockRegistration.toa_crate_green.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> toa_crate_black = BLOCK_ITEMS.register("toa_crate_black", () -> 
		new BlockItem(BlockRegistration.toa_crate_black.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> toa_crate_white = BLOCK_ITEMS.register("toa_crate_white", () -> 
		new BlockItem(BlockRegistration.toa_crate_white.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> toa_crate_brown = BLOCK_ITEMS.register("toa_crate_brown", () -> 
		new BlockItem(BlockRegistration.toa_crate_brown.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	//@formatter:on

//	public static final RegistryObject<Item> flag = BLOCK_ITEMS.register("flag", () -> 
//		new BlockItem(BlockInit.kanohi_pedestal.get(), new Item.Properties().group(BioniMineItemGroup.instance)));

	// Building Blocks
	public static final RegistryObject<Item> protosteel_plating = BLOCK_ITEMS.register("protosteel_plating", () -> new BlockItem(BlockRegistration.protosteel_plating.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> matanui_stone = BLOCK_ITEMS.register("matanui_stone", () -> new BlockItem(BlockRegistration.matanui_stone.get(), new Item.Properties().group(BioniMineItemGroup.instance)));

	// Ores
	public static final RegistryObject<Item> lightstone_ore = BLOCK_ITEMS.register("lightstone_ore", () -> new BlockItem(BlockRegistration.lightstone_ore.get(), new Item.Properties().group(BioniMineItemGroup.instance)));
	public static final RegistryObject<Item> protodermis_ore = BLOCK_ITEMS.register("protodermis_ore", () -> new BlockItem(BlockRegistration.protodermis_ore.get(), new Item.Properties().group(BioniMineItemGroup.instance)));

}
