package com.github.gresh113.bionimine.registration;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.entities.matoran.ElementalAffiliation;
import com.github.gresh113.bionimine.entities.matoran.MatoranProfession;

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
	public static final DeferredRegister<ElementalAffiliation> ELEMENTS = DeferredRegister.create(ElementalAffiliation.class, Bionimine.MODID);
	
	public static final RegistryObject<ElementalAffiliation> PO = ELEMENTS.register("po", () -> new ElementalAffiliation("po"));
	public static final RegistryObject<ElementalAffiliation> KO = ELEMENTS.register("ko", () -> new ElementalAffiliation("ko"));
	public static final RegistryObject<ElementalAffiliation> TA = ELEMENTS.register("ta", () -> new ElementalAffiliation("ta"));
	public static final RegistryObject<ElementalAffiliation> LE = ELEMENTS.register("le", () -> new ElementalAffiliation("le"));
	public static final RegistryObject<ElementalAffiliation> GA = ELEMENTS.register("ga", () -> new ElementalAffiliation("ga"));
	public static final RegistryObject<ElementalAffiliation> ONU = ELEMENTS.register("onu", () -> new ElementalAffiliation("onu"));
	
	
	public static final DeferredRegister<MatoranProfession> PROFESSIONS = DeferredRegister.create(MatoranProfession.class, Bionimine.MODID);
	
	public static final RegistryObject<MatoranProfession> NONE = PROFESSIONS.register("none", () -> MatoranProfession.NONE);
	
	
	@Mod.EventBusSubscriber(modid = Bionimine.MODID, bus = Bus.MOD)
	public static class EventHandler {

		@SubscribeEvent
		public static void createRegistry(final RegistryEvent.NewRegistry event) {
			new RegistryBuilder<ElementalAffiliation>().setName(new ResourceLocation(Bionimine.MODID, "element")).setType(ElementalAffiliation.class).create();;
			new RegistryBuilder<MatoranProfession>().setName(new ResourceLocation(Bionimine.MODID, "profession")).setType(MatoranProfession.class).create();
		}
	}

}
