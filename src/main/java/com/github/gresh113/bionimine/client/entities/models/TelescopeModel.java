package com.github.gresh113.bionimine.client.entities.models;

import com.github.gresh113.bionimine.entities.TelescopeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TelescopeModel extends EntityModel<TelescopeEntity> {
	private final ModelRenderer Boom;
	private final ModelRenderer Boom2;
	private final ModelRenderer Boom3;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public TelescopeModel() {
		textureWidth = 32;
		textureHeight = 40;

		Boom = new ModelRenderer(this);
		Boom.setRotationPoint(0.0F, 14.0F, -2.0F);
		setRotationAngle(Boom, 0.4363F, 0.0F, 0.0F);
		Boom.setTextureOffset(16, 1).addBox(-2.0F, -8.0F, 0.0F, 4.0F, 8.0F, 2.0F, 0.0F, false);

		Boom2 = new ModelRenderer(this);
		Boom2.setRotationPoint(0.0F, 7.6218F, -9.3978F);
		setRotationAngle(Boom2, 2.8798F, 0.0F, 0.0F);
		Boom2.setTextureOffset(16, 14).addBox(-2.0F, -1.7412F, -1.4659F, 4.0F, 5.0F, 1.0F, 0.0F, false);

		Boom3 = new ModelRenderer(this);
		Boom3.setRotationPoint(0.0F, 8.2076F, -14.3978F);
		setRotationAngle(Boom3, -1.9199F, 0.0F, 0.0F);
		Boom3.setTextureOffset(16, 20).addBox(-2.0F, -5.114F, -1.5666F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 4.5F, -14.5F);
		setRotationAngle(bone, -0.6109F, 0.0F, 0.0F);
		bone.setTextureOffset(26, 14).addBox(-1.0F, -5.9419F, -1.2699F, 2.0F, 5.0F, 1.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 4.0F, -12.0F);
		setRotationAngle(bone2, -0.1745F, -0.6981F, -0.9599F);
		bone2.setTextureOffset(15, 32).addBox(-1.634F, -2.2929F, -1.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Boom.render(matrixStack, buffer, packedLight, packedOverlay);
		Boom2.render(matrixStack, buffer, packedLight, packedOverlay);
		Boom3.render(matrixStack, buffer, packedLight, packedOverlay);
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
		bone2.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(TelescopeEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// TODO Auto-generated method stub
		
	}
}