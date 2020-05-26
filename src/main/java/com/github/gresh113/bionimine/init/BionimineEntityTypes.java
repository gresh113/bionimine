package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.entities.fikou_spider.FikouSpiderEntity;
import com.github.gresh113.bionimine.entities.matoran.MatoranEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BionimineEntityTypes {
	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, BioniMine.MODID);
	
	
	public static final RegistryObject<EntityType<FikouSpiderEntity>> FIKOU_SPIDER = ENTITY_TYPES.register("fikou_spider", () ->
	(EntityType.Builder.<FikouSpiderEntity>create(FikouSpiderEntity :: new, 
			EntityClassification.MONSTER).size(0.9f, 1.3f).build(new ResourceLocation(BioniMine.MODID, "fikou_spider").toString())));
	
	public static final RegistryObject<EntityType<MatoranEntity>> MATORAN = ENTITY_TYPES.register("matoran", () ->
	(EntityType.Builder.<MatoranEntity>create(MatoranEntity :: new, 
			EntityClassification.CREATURE).size(.75f, 1.5f).build(new ResourceLocation(BioniMine.MODID, "matoran").toString())));

}
