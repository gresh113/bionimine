package com.github.gresh113.bionimine.client.entities.models.elementalprojectiles;

import com.github.gresh113.bionimine.entities.ElementalProjectileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class EarthProjectileModel extends EntityModel<ElementalProjectileEntity> {
	private final ModelRenderer Dirt;

	public EarthProjectileModel() {
		textureWidth = 64;
		textureHeight = 32;

		Dirt = new ModelRenderer(this);
		Dirt.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		Dirt.setRotationPoint(8.0F, 8.0F, 8.0F);
	}

	@Override
	public void setRotationAngles(ElementalProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Dirt.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}