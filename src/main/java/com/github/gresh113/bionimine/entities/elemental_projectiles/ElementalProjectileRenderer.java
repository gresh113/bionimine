package com.github.gresh113.bionimine.entities.elemental_projectiles;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.toa_gear.elemental_abilities.Elements;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElementalProjectileRenderer<T extends ElementalProjectileEntity> extends EntityRenderer<T> {
	protected final ResourceLocation TEXTURE = new ResourceLocation(Bionimine.MODID, "textures/entity/ice_projectile.png");

	public ElementalProjectileRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw)));
		EntityModel<? extends ElementalProjectileEntity> model = this.getModel(entityIn);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(model.getRenderType(this.getEntityTexture(entityIn)));
		model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) {
		Elements element = entity.getElement();
		if (element == null)
			element = Elements.FIRE;
		//Bionimine.LOGGER.info(element.getName());
		ResourceLocation loc = new ResourceLocation(Bionimine.MODID, "textures/entity/" + element.getName() + "_projectile.png");
		return loc;
		
	}

	public EntityModel<? extends ElementalProjectileEntity> getModel(T entity) {
		Elements element = entity.getElement();
		if (element == null)
			element = Elements.FIRE;
		return element.getAbilityHolder().getProjectileHandler().getProjectileModel();
	}
}
