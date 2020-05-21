package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.entities.fikou_spider.FikouSpider;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BionimineEntityTypes {
	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, BioniMine.MODID);
	
	
	public static final RegistryObject<EntityType<FikouSpider>> FIKOU_SPIDER = ENTITY_TYPES.register("fikou_spider", () ->
	(EntityType.Builder.<FikouSpider>create(FikouSpider :: new, 
			EntityClassification.CREATURE).size(0.9f, 1.3f).build(new ResourceLocation(BioniMine.MODID, "fikou_spider").toString())));

}
