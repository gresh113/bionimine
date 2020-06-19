package com.github.gresh113.bionimine.entities.elemental_projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class WaterProjectileModel extends EntityModel<ElementalProjectileEntity> {
	private final ModelRenderer Water_Ball;

	public WaterProjectileModel() {
		textureWidth = 24;
		textureHeight = 12;

		Water_Ball = new ModelRenderer(this);
		Water_Ball.setRotationPoint(0.0F, 0.0F, 0.0F);
		Water_Ball.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ElementalProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Water_Ball.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}