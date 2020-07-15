package com.github.gresh113.bionimine.client.entities.renderers;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.client.entities.models.TuragaModel;
import com.github.gresh113.bionimine.entities.turaga.TuragaEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TuragaRenderer extends MobRenderer<TuragaEntity, TuragaModel<TuragaEntity>>{
	
	protected final ResourceLocation TEXTURE = new ResourceLocation(Bionimine.MODID, "textures/entity/turaga/turaga.png");

	public TuragaRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new TuragaModel<TuragaEntity>(), 0.5f);
		this.addLayer(new MatoranHeldItemLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(TuragaEntity entity) {
		return new ResourceLocation(Bionimine.MODID, "textures/entity/turaga/" +  entity.getTexture() + ".png");
	}

}
