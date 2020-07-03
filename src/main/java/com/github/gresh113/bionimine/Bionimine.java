package com.github.gresh113.bionimine;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.gresh113.bionimine.api.BionimineRegistries;
import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyStorage;
import com.github.gresh113.bionimine.client.gui.KanohiOverlayHandler;
import com.github.gresh113.bionimine.entities.BionimineEntityTypes;
import com.github.gresh113.bionimine.entities.matoran.MatoranElement;
import com.github.gresh113.bionimine.entities.matoran.MatoranProfession;
import com.github.gresh113.bionimine.init.BlockInit;
import com.github.gresh113.bionimine.init.BlockItemInit;
import com.github.gresh113.bionimine.init.ItemInit;
import com.github.gresh113.bionimine.init.MatoranRegistration;
import com.github.gresh113.bionimine.inventory.container.BionimineContainerTypes;
import com.github.gresh113.bionimine.network.BioniminePacketHandler;
import com.github.gresh113.bionimine.objects.tileentity.BionimineTileEntityTypes;
import com.github.gresh113.bionimine.objects.toagear.kanohi.EquipmentEventChecker;
import com.github.gresh113.bionimine.objects.toagear.kanohi.KanohiInit;
import com.github.gresh113.bionimine.util.KeyHandler;
import com.github.gresh113.bionimine.world.gen.ProtodermisOreGen;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
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
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

@Mod(Bionimine.MODID)
@Mod.EventBusSubscriber(modid = Bionimine.MODID, bus = Bus.MOD)
public class Bionimine {
	// setting Mod ID
	public static final String MODID = "bionimine";

	// Setting up Logger
	public static final Logger LOGGER = LogManager.getLogger();

	public static Bionimine instance;


	public Bionimine() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		// Register the setup method for modloading
		modEventBus.addListener(this::setup);
		// Register the doClientStuff method for modloading
		modEventBus.addListener(this::doClientStuff);

		BionimineTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
		BionimineContainerTypes.CONTAINER_TYPES.register(modEventBus);
		BionimineEntityTypes.ENTITY_TYPES.register(modEventBus);
		BlockInit.BLOCKS.register(modEventBus);
		BlockItemInit.BLOCK_ITEMS.register(modEventBus);
		ItemInit.ITEMS.register(modEventBus);
		//KanohiInit.KANOHI.register(modEventBus);
		
		MinecraftForge.EVENT_BUS.register(new KanohiOverlayHandler());
		MinecraftForge.EVENT_BUS.register(new EquipmentEventChecker());
		
		MatoranRegistration.initialise(modEventBus);
		
		
		

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private static class ToaEnergyFactory implements Callable<IToaEnergy> {
		@Override
		public IToaEnergy call() throws Exception {
			return new ToaEnergy();
		}

	}

	@SubscribeEvent
	public void setup(final FMLCommonSetupEvent event) {
		//RenderTypeLookup.setRenderLayer(BlockInit.kanohi_pedestal.get(), RenderType.getCutout());
		CapabilityManager.INSTANCE.register(IToaEnergy.class, new ToaEnergyStorage(), new ToaEnergyFactory());
		BioniminePacketHandler.init();
	}

	@SubscribeEvent
	public void doClientStuff(final FMLClientSetupEvent event) {
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

	}

	// Creative Inventory tab for the BioniMine mod
	public static class BioniMineItemGroup extends ItemGroup {
		public static final BioniMineItemGroup instance = new BioniMineItemGroup(ItemGroup.GROUPS.length, "bionimine");

		private BioniMineItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.lightstone_item);
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
			// LOGGER.info("HELLO from Register Block");
		}
		
		@SubscribeEvent
		public static void initRegisters(RegistryEvent.NewRegistry event){
			
			//BionimineRegistries.MATORAN_ELEMENTS = (ForgeRegistry<MatoranElement>) new RegistryBuilder<MatoranElement>().setName(new ResourceLocation(Bionimine.MODID, "element")).setType(MatoranElement.class).create();
			//BionimineRegistries.MATORAN_PROFESSIONS = (ForgeRegistry<MatoranProfession>) new RegistryBuilder<MatoranProfession>().setName(new ResourceLocation(Bionimine.MODID, "profession")).setType(MatoranProfession.class).create();
		}
		
		@SubscribeEvent
		public static void registerElements(RegistryEvent.Register<MatoranElement> event) {
			BionimineRegistries.MATORAN_ELEMENTS = (ForgeRegistry<MatoranElement>) RegistryManager.ACTIVE.getRegistry(MatoranElement.class);
			
			//Bionimine.LOGGER.info("Got the thing: " + MatoranRegistration.TA.get().getName());
			
		}
		
		@SubscribeEvent
		public static void registerProfessions(RegistryEvent.Register<MatoranProfession> event) {
			BionimineRegistries.MATORAN_PROFESSIONS =  (ForgeRegistry<MatoranProfession>) RegistryManager.ACTIVE.getRegistry(MatoranProfession.class);
			
			
		}
	}
}
