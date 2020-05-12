package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.BioniMine.BioniMineItemGroup;
import com.github.gresh113.bionimine.objects.blocks.KanohiPedestalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = BioniMine.MODID, bus = Bus.MOD)
@ObjectHolder(BioniMine.MODID)
public class BlockInit {
	public static final Block protosteel_plating = null;
	public static final Block lightstone_ore = null;
	public static final Block kanohi_pedestal = null;
	//public static final Block kanohi_wall_pedestal = null;

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) // Register blocks
	{
		event.getRegistry().register(
				new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5, 30).sound(SoundType.METAL))
					.setRegistryName("protosteel_plating"));
		event.getRegistry().register(
				new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F))
					.setRegistryName("lightstone_ore"));
		event.getRegistry().register(
				new KanohiPedestalBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F))
					.setRegistryName("kanohi_pedestal"));
		/**event.getRegistry().register(
				new KanohiPedestal(KanohiPedestal.Types.KANOHIPEDESTAL, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F))
					.setRegistryName("kanohi_pedestal"));
		event.getRegistry().register(
				new KanohiWallPedestal(KanohiPedestal.Types.KANOHIPEDESTAL, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F))
					.setRegistryName("kanohi_wall_pedestal")); **/
	}

	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event) // Register items for blocks
	{
		event.getRegistry()
				.register(new BlockItem(lightstone_ore, new Item.Properties().group(BioniMineItemGroup.instance))
						.setRegistryName("lightstone_ore"));
		event.getRegistry()
				.register(new BlockItem(protosteel_plating, new Item.Properties().group(BioniMineItemGroup.instance))
						.setRegistryName("protosteel_plating"));
		event.getRegistry()
			.register(new BlockItem(kanohi_pedestal, new Item.Properties().group(BioniMineItemGroup.instance))
						.setRegistryName("kanohi_pedestal"));
	/**	event.getRegistry()
		.register(new WallOrFloorItem(BlockInit.kanohi_pedestal, BlockInit.kanohi_wall_pedestal, new Item.Properties().group(BioniMineItemGroup.instance).rarity(Rarity.UNCOMMON))
				.setRegistryName("kanohi_pedestal")); **/
	}
}
