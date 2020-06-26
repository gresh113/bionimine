package com.github.gresh113.bionimine.registration;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.entities.matoran.MatoranElement;
import com.github.gresh113.bionimine.entities.matoran.MatoranProfession;
import com.github.gresh113.bionimine.util.LazyForgeRegistry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;



public class MatoranRegistration {
	private static boolean isInitialised = false;
	public static void initialise(final IEventBus modEventBus) {
		
		if (isInitialised) {
			throw new IllegalStateException("Already initialised");
		}

		ELEMENTS.register(modEventBus);
		PROFESSIONS.register(modEventBus);

		isInitialised = true;
	}
	public static final DeferredRegister<MatoranElement> ELEMENTS = DeferredRegister.create(LazyForgeRegistry.of(MatoranElement.class), Bionimine.MODID);
	
	public static final RegistryObject<MatoranElement> PO = ELEMENTS.register("po", () -> new MatoranElement("po"));
	public static final RegistryObject<MatoranElement> KO = ELEMENTS.register("ko", () -> new MatoranElement("ko"));
	public static final RegistryObject<MatoranElement> TA = ELEMENTS.register("ta", () -> new MatoranElement("ta"));
	public static final RegistryObject<MatoranElement> LE = ELEMENTS.register("le", () -> new MatoranElement("le"));
	public static final RegistryObject<MatoranElement> GA = ELEMENTS.register("ga", () -> new MatoranElement("ga"));
	public static final RegistryObject<MatoranElement> ONU = ELEMENTS.register("onu", () -> new MatoranElement("onu"));
	
	
	public static final DeferredRegister<MatoranProfession> PROFESSIONS = DeferredRegister.create(LazyForgeRegistry.of(MatoranProfession.class), Bionimine.MODID);
	
	public static final RegistryObject<MatoranProfession> NONE = PROFESSIONS.register("none", () -> MatoranProfession.NONE);
	
	
	@Mod.EventBusSubscriber(modid = Bionimine.MODID, bus = Bus.MOD)
	public static class EventHandler {

		@SubscribeEvent
		public static void createRegistry(final RegistryEvent.NewRegistry event) {
			new RegistryBuilder<MatoranElement>().setName(new ResourceLocation(Bionimine.MODID, "element")).setType(MatoranElement.class).create();;
			new RegistryBuilder<MatoranProfession>().setName(new ResourceLocation(Bionimine.MODID, "profession")).setType(MatoranProfession.class).create();
		}
	}

}
