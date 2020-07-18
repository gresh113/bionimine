package com.github.gresh113.bionimine.client.entities.renderers;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.client.entities.models.MuakaModel;
import com.github.gresh113.bionimine.entities.MuakaEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MuakaRenderer extends MobRenderer<MuakaEntity, MuakaModel<MuakaEntity>>{

	protected final ResourceLocation TEXTURE = new ResourceLocation(Bionimine.MODID, "textures/entity/muaka.png");

	public MuakaRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new MuakaModel<MuakaEntity>(), 0.5f);
		}
	
	@Override
	public ResourceLocation getEntityTexture(MuakaEntity entity) {
		return TEXTURE;
		}
}
