package com.github.gresh113.bionimine.client.entities.models;
import com.github.gresh113.bionimine.entities.ElementalProjectileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class IceProjectileModel extends EntityModel<ElementalProjectileEntity> {
	private final ModelRenderer spike;

	public IceProjectileModel() {
		textureWidth = 16;
		textureHeight = 16;

		spike = new ModelRenderer(this);
		spike.setRotationPoint(0.0F, 0.0F, 0.0F);
		spike.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -4.0F, 2.0F, 2.0F, 9.0F, 0.0F, false);
		
	}

	@Override
	public void setRotationAngles(ElementalProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		spike.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
		
	}
}