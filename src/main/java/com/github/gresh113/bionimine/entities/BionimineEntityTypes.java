package com.github.gresh113.bionimine.entities;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.entities.elemental_projectiles.ElementalProjectileEntity;
import com.github.gresh113.bionimine.entities.elemental_projectiles.ElementalProjectileRenderer;
import com.github.gresh113.bionimine.entities.fikou_spider.FikouSpiderEntity;
import com.github.gresh113.bionimine.entities.fikou_spider.FikouSpiderRenderer;
import com.github.gresh113.bionimine.entities.husi.HusiEntity;
import com.github.gresh113.bionimine.entities.husi.HusiRenderer;
import com.github.gresh113.bionimine.entities.kanoka_disk.BambooDiskRenderer;
import com.github.gresh113.bionimine.entities.kanoka_disk.KanokaEntity;
import com.github.gresh113.bionimine.entities.matoran.MatoranEntity;
import com.github.gresh113.bionimine.entities.matoran.MatoranRenderer;
import com.github.gresh113.bionimine.entities.telescope.TelescopeEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
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

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Bionimine.MODID);

	public static final RegistryObject<EntityType<FikouSpiderEntity>> FIKOU_SPIDER = ENTITY_TYPES.register("fikou_spider", () -> (EntityType.Builder.<FikouSpiderEntity>create(FikouSpiderEntity::new, EntityClassification.MONSTER).size(0.9f, 1.3f).build(new ResourceLocation(Bionimine.MODID, "fikou_spider").toString())));

	public static final RegistryObject<EntityType<HusiEntity>> HUSI = ENTITY_TYPES.register("husi", () -> (EntityType.Builder.<HusiEntity>create(HusiEntity::new, EntityClassification.CREATURE).size(0.9f, 1.3f).build(new ResourceLocation(Bionimine.MODID, "husi").toString())));

	public static final RegistryObject<EntityType<MatoranEntity>> MATORAN = ENTITY_TYPES.register("matoran", () -> (EntityType.Builder.<MatoranEntity>create(MatoranEntity::new, EntityClassification.CREATURE).size(.75f, 1.5f).build(new ResourceLocation(Bionimine.MODID, "matoran").toString())));

	public static final RegistryObject<EntityType<TelescopeEntity>> TELESCOPE = ENTITY_TYPES.register("telescope", () -> (EntityType.Builder.<TelescopeEntity>create(TelescopeEntity::new, EntityClassification.MISC).size(.75f, 1.5f).build(new ResourceLocation(Bionimine.MODID, "telescope").toString())));

	public static final RegistryObject<EntityType<KanokaEntity>> KANOKA = ENTITY_TYPES.register("kanoka", () -> (EntityType.Builder.<KanokaEntity>create(KanokaEntity::new, EntityClassification.MISC).size(1.0f, 0.0625f).build(new ResourceLocation(Bionimine.MODID, "kanoka").toString())));

	public static final RegistryObject<EntityType<ElementalProjectileEntity>> ELEMENTAL_PROJECTILE = ENTITY_TYPES.register("elemental_projectile", () -> (EntityType.Builder.<ElementalProjectileEntity>create(ElementalProjectileEntity::new, EntityClassification.MISC).size(.05f, 0.05f).build(new ResourceLocation(Bionimine.MODID, "elemental_projectile").toString())));
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(KANOKA.get(), BambooDiskRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ELEMENTAL_PROJECTILE.get(), ElementalProjectileRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(FIKOU_SPIDER.get(), FikouSpiderRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(MATORAN.get(), MatoranRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(HUSI.get(), HusiRenderer::new);

		// for (KanohiShape shape : KanohiShape.values())
		// {BioniMine.LOGGER.info("{|predicate|: {|bionimine:shape|: " +
		// shape.getPredicate() + "}, |model|:
		// |bionimine:item/masks/"+shape.getName()+"|},");}
	}

}
