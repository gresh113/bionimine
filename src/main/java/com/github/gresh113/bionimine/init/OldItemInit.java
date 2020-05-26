package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.BioniMine.BioniMineItemGroup;
import com.github.gresh113.bionimine.kanohi.OldKanohiItem;
import com.github.gresh113.bionimine.kanohi.KanohiItem;
import com.github.gresh113.bionimine.kanohi.OldKanohiItem.KanohiMaterial;
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
public class OldItemInit {
	public static final Item bamboo_disk = null;
	public static final Item fire_sword = null;
	public static final Item chroniclers_staff = null;
	// public static final Item digger = null;
	public static final Item disk_launcher = null;
	public static final Item flute = null;
	// public static final Item hatchet = null;
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
	public static final Item cowrie_shell = null;
	public static final Item fishhook = null;
	public static final Item flax = null;
	public static final Item harakeke = null;
	public static final Item nail = null;
	public static final Item net = null;
	public static final Item ore = null;
	public static final Item rigging = null;
	public static final Item rope = null;
	public static final Item sailcloth = null;

}
