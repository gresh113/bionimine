package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.BioniMine.BioniMineItemGroup;
import com.github.gresh113.bionimine.kanohi.ArmorPalette;
import com.github.gresh113.bionimine.objects.items.AirBladderItem;
import com.github.gresh113.bionimine.objects.items.ToaArmorItem;

import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, BioniMine.MODID);
	
	static Item.Properties defaultItemProperties = new Item.Properties().group(BioniMineItemGroup.instance);

	public static final RegistryObject<Item> toa_armor_fire = ITEMS.register("toa_armor_fire",
			() -> new ToaArmorItem(ArmorPalette.RED));
	public static final RegistryObject<Item> toa_armor_water = ITEMS.register("toa_armor_water",
			() -> new ToaArmorItem(ArmorPalette.BLUE));
	public static final RegistryObject<Item> toa_armor_air = ITEMS.register("toa_armor_air",
			() -> new ToaArmorItem(ArmorPalette.GREEN));
	public static final RegistryObject<Item> toa_armor_earth = ITEMS.register("toa_armor_earth",
			() -> new ToaArmorItem(ArmorPalette.BLACK));
	public static final RegistryObject<Item> toa_armor_stone = ITEMS.register("toa_armor_stone",
			() -> new ToaArmorItem(ArmorPalette.BROWN));
	public static final RegistryObject<Item> toa_armor_ice = ITEMS.register("toa_armor_ice",
			() -> new ToaArmorItem(ArmorPalette.WHITE));

	public static final RegistryObject<Item> heatstone = ITEMS.register("heatstone",
			() -> new FlintAndSteelItem(new Item.Properties().maxStackSize(1).group(BioniMineItemGroup.instance)));
	
	public static final RegistryObject<Item> flag = ITEMS.register("flag",
			() -> new Item(defaultItemProperties));
	
	public static final RegistryObject<Item> air_bladder = ITEMS.register("air_bladder",
			() -> new AirBladderItem());
	public static final RegistryObject<Item> bamboo_pole = ITEMS.register("bamboo_pole",
			() -> new Item(defaultItemProperties));
	public static Item lightstone_item = new Item(defaultItemProperties);
	public static final RegistryObject<Item> lightstone = ITEMS.register("lightstone",
			() -> lightstone_item);
	public static final RegistryObject<Item> protosteel = ITEMS.register("protosteel",
			() -> new Item(defaultItemProperties));
	public static final RegistryObject<Item> protodermis = ITEMS.register("protodermis",
			() -> new Item(defaultItemProperties));

}
