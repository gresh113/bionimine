package com.github.gresh113.bionimine.entities.fikou_spider;

import com.github.gresh113.bionimine.Bionimine;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FikouSpiderRenderer extends MobRenderer<FikouSpiderEntity, FikouSpiderModel<FikouSpiderEntity>>{

	protected final ResourceLocation TEXTURE = new ResourceLocation(Bionimine.MODID, "textures/entity/fikou_spider.png");
	
	public FikouSpiderRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new FikouSpiderModel<FikouSpiderEntity>(), 0.5f);
		}
	
	@Override
	public ResourceLocation getEntityTexture(FikouSpiderEntity entity) {
		return TEXTURE;
		}
}
