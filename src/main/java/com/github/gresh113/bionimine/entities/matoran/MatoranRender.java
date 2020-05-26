package com.github.gresh113.bionimine.entities.matoran;

import com.github.gresh113.bionimine.BioniMine;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MatoranRender extends MobRenderer<MatoranEntity, MatoranModel<MatoranEntity>>{
	
	protected final ResourceLocation TEXTURE = new ResourceLocation(BioniMine.MODID, "textures/entity/matoran.png");

	public MatoranRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new MatoranModel<MatoranEntity>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(MatoranEntity entity) {
		return new ResourceLocation(BioniMine.MODID, "textures/entity/" +  entity.getTexture() + ".png");
	}

}
