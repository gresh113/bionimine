package com.github.gresh113.bionimine.entities.fikou_spider;

import com.github.gresh113.bionimine.BioniMine;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FikouSpiderRender extends MobRenderer<FikouSpider, FikouSpiderModel<FikouSpider>>{

	protected final ResourceLocation TEXTURE = new ResourceLocation(BioniMine.MODID, "textures/entity/fikou_spider.png");
	
	public FikouSpiderRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new FikouSpiderModel<FikouSpider>(), 0.5f	);
		}
	
	@Override
	public ResourceLocation getEntityTexture(FikouSpider entity) {
		return TEXTURE;
		}
}
