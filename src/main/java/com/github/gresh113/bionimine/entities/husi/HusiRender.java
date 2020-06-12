package com.github.gresh113.bionimine.entities.husi;

import com.github.gresh113.bionimine.Bionimine;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class HusiRender extends MobRenderer<HusiEntity, HusiModel<HusiEntity>> {
	protected final ResourceLocation TEXTURE = new ResourceLocation(Bionimine.MODID, "textures/entity/husi.png");
	
	public HusiRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new HusiModel<HusiEntity>(), 0.5f);
		}
	
	@Override
	public ResourceLocation getEntityTexture(HusiEntity entity) {
		return TEXTURE;
		}
}
