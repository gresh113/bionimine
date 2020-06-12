package com.github.gresh113.bionimine.entities.telescope;

import com.github.gresh113.bionimine.Bionimine;
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
