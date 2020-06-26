package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.Bionimine.BioniMineItemGroup;
import com.github.gresh113.bionimine.entities.BionimineEntityTypes;
import com.github.gresh113.bionimine.entities.ModdedSpawnEggItem;
import com.github.gresh113.bionimine.objects.items.AirBladderItem;
import com.github.gresh113.bionimine.objects.items.BambooDiskItem;
import com.github.gresh113.bionimine.objects.items.TelescopeItem;
import com.github.gresh113.bionimine.objects.toagear.ArmorPalette;
import com.github.gresh113.bionimine.objects.toagear.ToaArmorItem;
import com.github.gresh113.bionimine.objects.toagear.ToaTool;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.Elements;

import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Bionimine.MODID);

	static Item.Properties defaultItemProperties = new Item.Properties().group(BioniMineItemGroup.instance);

	public static final RegistryObject<Item> toa_armor_fire = ITEMS.register("toa_armor_fire", () -> new ToaArmorItem(ArmorPalette.RED, Elements.FIRE));
	public static final RegistryObject<Item> toa_armor_water = ITEMS.register("toa_armor_water", () -> new ToaArmorItem(ArmorPalette.BLUE, Elements.WATER));
	public static final RegistryObject<Item> toa_armor_air = ITEMS.register("toa_armor_air", () -> new ToaArmorItem(ArmorPalette.GREEN, Elements.AIR));
	public static final RegistryObject<Item> toa_armor_earth = ITEMS.register("toa_armor_earth", () -> new ToaArmorItem(ArmorPalette.BLACK, Elements.EARTH));
	public static final RegistryObject<Item> toa_armor_stone = ITEMS.register("toa_armor_stone", () -> new ToaArmorItem(ArmorPalette.BROWN, Elements.STONE));
	public static final RegistryObject<Item> toa_armor_ice = ITEMS.register("toa_armor_ice", () -> new ToaArmorItem(ArmorPalette.WHITE, Elements.ICE));

	public static final RegistryObject<Item> ice_sword = ITEMS.register("ice_sword", () -> new ToaTool(Elements.ICE));
	public static final RegistryObject<Item> fire_sword = ITEMS.register("fire_sword", () -> new ToaTool(Elements.FIRE));
	public static final RegistryObject<Item> air_axe = ITEMS.register("air_axe", () -> new ToaTool(Elements.AIR));
	public static final RegistryObject<Item> hooks = ITEMS.register("hooks", () -> new ToaTool(Elements.WATER));

	public static final RegistryObject<Item> heatstone = ITEMS.register("heatstone", () -> new FlintAndSteelItem(new Item.Properties().maxStackSize(1).group(BioniMineItemGroup.instance)));

	public static final RegistryObject<Item> husi_spawn_egg = ITEMS.register("husi_spawn_egg", () -> new ModdedSpawnEggItem(BionimineEntityTypes.HUSI, 16220436, 8790020, defaultItemProperties));

	public static final RegistryObject<Item> flag = ITEMS.register("flag", () -> new Item(defaultItemProperties));

	public static final RegistryObject<Item> telescope = ITEMS.register("telescope", () -> new TelescopeItem(defaultItemProperties));

	public static final RegistryObject<Item> air_bladder = ITEMS.register("air_bladder", () -> new AirBladderItem());
	public static final RegistryObject<Item> bamboo_pole = ITEMS.register("bamboo_pole", () -> new Item(defaultItemProperties));
	public static Item lightstone_item = new Item(defaultItemProperties); // Needs to be a separate item to be used as the group icon
	public static final RegistryObject<Item> lightstone = ITEMS.register("lightstone", () -> lightstone_item);
	public static final RegistryObject<Item> protosteel = ITEMS.register("protosteel", () -> new Item(defaultItemProperties));
	public static final RegistryObject<Item> protodermis = ITEMS.register("protodermis", () -> new Item(defaultItemProperties));
	public static final RegistryObject<Item> protodermis_ingot = ITEMS.register("protodermis_ingot", () -> new Item(defaultItemProperties));
	public static final RegistryObject<Item> protodermis_rod = ITEMS.register("protodermis_rod", () -> new Item(defaultItemProperties));
	public static final RegistryObject<Item> widgets = ITEMS.register("widgets", () -> new Item(defaultItemProperties));
	public static final RegistryObject<Item> madu_fruit = ITEMS.register("madu_fruit", () -> new Item(defaultItemProperties));

	public static final RegistryObject<Item> bamboo_disk = ITEMS.register("bamboo_disk", () -> new BambooDiskItem(new Item.Properties().maxStackSize(16).group(BioniMineItemGroup.instance)));

}
