package com.github.gresh113.bionimine.client.entities.renderers;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.client.entities.models.TelescopeModel;
import com.github.gresh113.bionimine.entities.TelescopeEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class TelescopeRender extends LivingRenderer<TelescopeEntity, TelescopeModel>{
	protected final ResourceLocation TEXTURE = new ResourceLocation(Bionimine.MODID, "textures/entity/telescope.png");
	
	public TelescopeRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new TelescopeModel(), 0.5f);
		}
	
	@Override
	public ResourceLocation getEntityTexture(TelescopeEntity entity) {
		return TEXTURE;
		}

}
