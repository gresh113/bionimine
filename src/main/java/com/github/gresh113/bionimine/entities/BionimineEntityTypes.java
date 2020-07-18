package com.github.gresh113.bionimine.entities;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.client.entities.renderers.*;
import com.github.gresh113.bionimine.entities.matoran.MatoranEntity;
import com.github.gresh113.bionimine.entities.turaga.TuragaEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Bionimine.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class BionimineEntityTypes {
	
	
	//@formatter:off
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Bionimine.MODID);

	
	public static final RegistryObject<EntityType<MatoranEntity>> MATORAN = 
			ENTITY_TYPES.register("matoran", () -> (
					EntityType.Builder.<MatoranEntity>create(MatoranEntity::new, EntityClassification.CREATURE).size(.75f, 1.5f)
					.build(new ResourceLocation(Bionimine.MODID, "matoran").toString())));
	
	public static final RegistryObject<EntityType<TuragaEntity>> TURAGA = 
			ENTITY_TYPES.register("turaga", () -> (
					EntityType.Builder.<TuragaEntity>create(TuragaEntity::new, EntityClassification.CREATURE).size(.75f, 1.5f)
					.build(new ResourceLocation(Bionimine.MODID, "turaga").toString())));
	
	
	public static final RegistryObject<EntityType<FikouSpiderEntity>> 
	FIKOU_SPIDER = ENTITY_TYPES.register("fikou_spider", () -> (
			EntityType.Builder.<FikouSpiderEntity>create(FikouSpiderEntity::new, EntityClassification.MONSTER).size(0.9f, 1.3f)
			.build(new ResourceLocation(Bionimine.MODID, "fikou_spider").toString())));

	public static final RegistryObject<EntityType<HusiEntity>> HUSI = 
			ENTITY_TYPES.register("husi", () -> (
					EntityType.Builder.<HusiEntity>create(HusiEntity::new, EntityClassification.CREATURE).size(0.9f, 1.3f)
					.build(new ResourceLocation(Bionimine.MODID, "husi").toString())));


	public static final RegistryObject<EntityType<MuakaEntity>> MUAKA =
			ENTITY_TYPES.register("muaka", () -> (
					EntityType.Builder.<MuakaEntity>create(MuakaEntity::new, EntityClassification.MONSTER).size(2.5F, 2.7F)
							.build(new ResourceLocation(Bionimine.MODID, "muaka").toString())));


	public static final RegistryObject<EntityType<TelescopeEntity>> TELESCOPE = 
			ENTITY_TYPES.register("telescope", () -> (
					EntityType.Builder.<TelescopeEntity>create(TelescopeEntity::new, EntityClassification.MISC).size(.75f, 1.5f)
					.build(new ResourceLocation(Bionimine.MODID, "telescope").toString())));
	
	
	public static final RegistryObject<EntityType<MaduCaboloEntity>> MADU_CABOLO = 
			ENTITY_TYPES.register("madu_cabolo", () -> (
					EntityType.Builder.<MaduCaboloEntity>create(MaduCaboloEntity::new, EntityClassification.MISC).size(1.0f, 0.0625f)
					.build(new ResourceLocation(Bionimine.MODID, "madu_cabolo").toString())));

	public static final RegistryObject<EntityType<KanokaEntity>> KANOKA = 
			ENTITY_TYPES.register("kanoka", () -> (
					EntityType.Builder.<KanokaEntity>create(KanokaEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).func_233606_a_(4).func_233608_b_(10)
					.build(new ResourceLocation(Bionimine.MODID, "kanoka").toString())));

	public static final RegistryObject<EntityType<ElementalProjectileEntity>> ELEMENTAL_PROJECTILE = 
			ENTITY_TYPES.register("elemental_projectile", () -> (
					EntityType.Builder.<ElementalProjectileEntity>create(ElementalProjectileEntity::new, EntityClassification.MISC).size(.05f, 0.05f)
					.build(new ResourceLocation(Bionimine.MODID, "elemental_projectile").toString())));
	
	public static final RegistryObject<EntityType<ElementalSpikesEntity>> ELEMENTAL_SPIKES = 
			ENTITY_TYPES.register("elemental_spikes", () -> (
					EntityType.Builder.<ElementalSpikesEntity>create(ElementalSpikesEntity::new, EntityClassification.MISC).size(1.0f, 1.0f)
					.build(new ResourceLocation(Bionimine.MODID, "elemental_spikes").toString())));
	//@formatter:on
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(MATORAN.get(), MatoranRenderer::new);
		GlobalEntityTypeAttributes.put(MATORAN.get(), MatoranEntity.assignAttributes().func_233813_a_());
		RenderingRegistry.registerEntityRenderingHandler(TURAGA.get(), TuragaRenderer::new);
		GlobalEntityTypeAttributes.put(TURAGA.get(), TuragaEntity.assignAttributes().func_233813_a_());

		RenderingRegistry.registerEntityRenderingHandler(FIKOU_SPIDER.get(), FikouSpiderRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(HUSI.get(), HusiRenderer::new);
		GlobalEntityTypeAttributes.put(HUSI.get(), HusiEntity.assignAttributes().func_233813_a_());
		RenderingRegistry.registerEntityRenderingHandler(MUAKA.get(), MuakaRenderer::new);
		GlobalEntityTypeAttributes.put(MUAKA.get(), MuakaEntity.assignAttributes().func_233813_a_());
		
		RenderingRegistry.registerEntityRenderingHandler(KANOKA.get(), BambooDiskRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ELEMENTAL_PROJECTILE.get(), ElementalProjectileRenderer::new);
		//RenderingRegistry.registerEntityRenderingHandler(ELEMENTAL_SPIKES.get(), ElementalSpikesRenderer::new);
		

	}

}
