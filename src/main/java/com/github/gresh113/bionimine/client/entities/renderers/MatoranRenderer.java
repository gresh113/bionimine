package com.github.gresh113.bionimine.client.entities.renderers;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.client.entities.models.MatoranModel;
import com.github.gresh113.bionimine.entities.matoran.MatoranEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MatoranRenderer extends MobRenderer<MatoranEntity, MatoranModel<MatoranEntity>>{
	
	protected final ResourceLocation TEXTURE = new ResourceLocation(Bionimine.MODID, "textures/entity/matoran/matoran.png");

	public MatoranRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new MatoranModel<MatoranEntity>(), 0.5f);
		this.addLayer(new MatoranHeldItemLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(MatoranEntity entity) {
		return new ResourceLocation(Bionimine.MODID, "textures/entity/matoran/" +  entity.getTexture() + ".png");
	}

}
