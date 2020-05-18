package com.github.gresh113.bionimine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.gresh113.bionimine.init.BionimineTileEntityTypes;
import com.github.gresh113.bionimine.init.BlockInit;
import com.github.gresh113.bionimine.init.BlockItemInit;
import com.github.gresh113.bionimine.init.ItemInit;
import com.github.gresh113.bionimine.init.KanohiInit;
import com.github.gresh113.bionimine.inventory.container.BionimineContainerTypes;
import com.github.gresh113.bionimine.world.gen.ProtodermisOreGen;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BioniMine.MODID)
@Mod.EventBusSubscriber(modid = BioniMine.MODID, bus = Bus.MOD)
public class BioniMine {
	// setting Mod ID
	public static final String MODID = "bionimine";

	// Setting up Logger
	public static final Logger LOGGER = LogManager.getLogger();

	public static BioniMine instance;

	public BioniMine() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		// Register the setup method for modloading
		modEventBus.addListener(this::setup);
		// Register the doClientStuff method for modloading
		modEventBus.addListener(this::doClientStuff);

		BionimineTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
		BionimineContainerTypes.CONTAINER_TYPES.register(modEventBus);
		BlockInit.BLOCKS.register(modEventBus);
		BlockItemInit.BLOCK_ITEMS.register(modEventBus);
		//KanohiInit.KANOHI.register(modEventBus);

		instance = this;

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		RenderTypeLookup.setRenderLayer(BlockInit.kanohi_pedestal.get(), RenderType.getCutout());
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		// do something that can only be done on the client
		LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
		KeyHandler.registerKeys();
	}
	
	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
		ProtodermisOreGen.generateOre();
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
		// For other mod compatibility
		// some example code to dispatch IMC to another mod
		// InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello
		// world from the MDK"); return "Hello world";});
	}

	private void processIMC(final InterModProcessEvent event) {
		// For other mod compatibility
		// some example code to receive and process InterModComms from other mods
		// LOGGER.info("Got IMC {}", event.getIMCStream().
		// map(m->m.getMessageSupplier().get()).
		// collect(Collectors.toList()));
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// do something when the server starts

	}

	// Creative Inventory tab for the BioniMine mod
	public static class BioniMineItemGroup extends ItemGroup {
		public static final BioniMineItemGroup instance = new BioniMineItemGroup(ItemGroup.GROUPS.length, "bionimine");

		private BioniMineItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.lightstone);
		}
	}
	
	// Creative Inventory tab for Kanohi Items
		public static class KanohiItemGroup extends ItemGroup {
			public static final KanohiItemGroup instance = new KanohiItemGroup(ItemGroup.GROUPS.length, "kanohi");

			private KanohiItemGroup(int index, String label) {
				super(index, label);
			}

			@Override
			public ItemStack createIcon() {
				return new ItemStack(KanohiInit.PowerlessMaskItem);
			}
		}

	// You can use EventBusSubscriber to automatically subscribe events on the
	// contained class (this is subscribing to the MOD
	// Event bus for receiving Registry Events)
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			// register a new block here
			//LOGGER.info("HELLO from Register Block");
		}
	}
}
