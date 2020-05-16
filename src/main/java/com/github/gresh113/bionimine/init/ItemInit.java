package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.BioniMine.BioniMineItemGroup;
import com.github.gresh113.bionimine.kanohi.KanohiItem;
import com.github.gresh113.bionimine.kanohi.NewKanohiItem;
import com.github.gresh113.bionimine.kanohi.KanohiItem.KanohiMaterial;
import com.github.gresh113.bionimine.objects.items.AirBladderItem;

import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = BioniMine.MODID, bus = Bus.MOD)
@ObjectHolder(BioniMine.MODID) // Makes this class an ObjectHolder for items of the bionimine mod
public class ItemInit {
	// Materials
	public static final Item protosteel = null;

	// Tools
	public static final Item air_bladder = null;
	public static final Item bamboo_disk = null;
	public static final Item book_of_chronicles = null;
	public static final Item fire_sword = null;
	public static final Item chroniclers_staff = null;
	// public static final Item digger = null;
	public static final Item disk_launcher = null;
	public static final Item flute = null;
	// public static final Item hatchet = null;
	public static final Item heatstone = null;
	public static final Item kolhii_ball = null;
	public static final Item infected_kolhii_ball = null;
	public static final Item kolhii_stick = null;
	public static final Item lavaboard = null;
	public static final Item makoki_stone = null;
	public static final Item makoki_stone_fragment = null;
	public static final Item memory_crystal = null;
	// public static final Item pickaxe = null;
	public static final Item sickle = null;
	public static final Item sluice = null;
	public static final Item toa_stone = null;

	// Supplies
	public static final Item bamboo_pole = null;
	public static final Item cowrie_shell = null;
	public static final Item fishhook = null;
	public static final Item flax = null;
	public static final Item harakeke = null;
	public static final Item lightstone = null;
	public static final Item nail = null;
	public static final Item net = null;
	public static final Item ore = null;
	public static final Item protodermis = null;
	public static final Item rigging = null;
	public static final Item rope = null;
	public static final Item sailcloth = null;
	// public static final Item seaweed= null;

	// charms

	// crystals

	public static final Item widget = null;

	// Kanohi
	public static final Item akaku = null;
	//public static final Item avohkii = null;
	public static final Item hau = null;
	public static final Item kakama = null;
	public static final Item kaukau = null;
	public static final Item krahkhan = null;
	public static final Item miru = null;
	public static final Item pakari = null;
	public static final Item vahi = null;
	// Nuva

	// Noble

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		
		Item.Properties defaultItemProperties = new Item.Properties().group(BioniMineItemGroup.instance);

		// Tools
		event.getRegistry().register(
				new FlintAndSteelItem(new Item.Properties().maxStackSize(1).group(BioniMineItemGroup.instance))
						.setRegistryName("heatstone"));
		event.getRegistry().register(
				new FlintAndSteelItem(new Item.Properties().maxStackSize(1).group(BioniMineItemGroup.instance))
						.setRegistryName("fire_sword"));

		// Materials
		event.getRegistry().register(
				new AirBladderItem().setRegistryName("air_bladder"));
		event.getRegistry().register(
				new Item(defaultItemProperties).setRegistryName("bamboo_pole"));
		event.getRegistry().register(
				new Item(defaultItemProperties).setRegistryName("flag"));
		event.getRegistry().register(
				new Item(defaultItemProperties).setRegistryName("lightstone"));
		event.getRegistry().register(
				new Item(defaultItemProperties).setRegistryName("protosteel"));
		event.getRegistry().register(
				new Item(defaultItemProperties).setRegistryName("protodermis"));

		// Kanohi
		event.getRegistry().register(new KanohiItem(KanohiMaterial.hau).setRegistryName("hau"));
		event.getRegistry().register(new KanohiItem(KanohiMaterial.miru).setRegistryName("miru"));
		event.getRegistry().register(new KanohiItem(KanohiMaterial.akaku).setRegistryName("akaku"));
		event.getRegistry().register(new KanohiItem(KanohiMaterial.kaukau).setRegistryName("kaukau"));
		event.getRegistry().register(new KanohiItem(KanohiMaterial.pakari).setRegistryName("pakari"));
		event.getRegistry().register(new KanohiItem(KanohiMaterial.kakama).setRegistryName("kakama"));
		
		//event.getRegistry().register(new NewKanohiItem().setRegistryName("avokhii"));
	}

}
