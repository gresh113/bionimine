package com.github.gresh113.bionimine.entities.matoran;

import com.github.gresh113.bionimine.Bionimine;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MatoranRenderer extends MobRenderer<MatoranEntity, MatoranModel<MatoranEntity>>{
	
	protected final ResourceLocation TEXTURE = new ResourceLocation(Bionimine.MODID, "textures/entity/matoran/matoran.png");

	public MatoranRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new MatoranModel<MatoranEntity>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(MatoranEntity entity) {
		return new ResourceLocation(Bionimine.MODID, "textures/entity/matoran/" +  entity.getTexture() + ".png");
	}

}
