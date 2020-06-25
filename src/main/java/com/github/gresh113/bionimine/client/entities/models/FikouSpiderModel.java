package com.github.gresh113.bionimine.client.entities.models;

import com.github.gresh113.bionimine.entities.FikouSpiderEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class FikouSpiderModel<T extends FikouSpiderEntity> extends EntityModel<T> {
	private final ModelRenderer Body;
	private final ModelRenderer Shell;
	private final ModelRenderer Head;
	private final ModelRenderer Eyes;
	private final ModelRenderer Legs;
	private final ModelRenderer legR;
	private final ModelRenderer legL;

	public FikouSpiderModel() {
		textureWidth = 32;
		textureHeight = 32;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Body, 0.0F, 3.1416F, 0.0F);
		Body.setTextureOffset(0, 0).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);

		Shell = new ModelRenderer(this);
		Shell.setRotationPoint(4.0F, -1.0F, -8.0F);
		Body.addChild(Shell);
		Shell.setTextureOffset(0, 5).addBox(-8.0F, -5.0F, 0.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		Shell.setTextureOffset(0, 15).addBox(-7.0F, -6.0F, 0.0F, 6.0F, 1.0F, 8.0F, 0.0F, false);
		Shell.setTextureOffset(0, 15).addBox(-7.0F, -3.0F, 0.0F, 6.0F, 1.0F, 8.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -4.0F, 2.0F);
		Body.addChild(Head);
		setRotationAngle(Head, 0.0F, 0.5236F, 0.0F);
		Head.setTextureOffset(48, 4).addBox(-3.0F, -1.0F, 0.0F, 6.0F, 1.0F, 2.0F, 0.0F, false);
		Head.setTextureOffset(40, 4).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Head.setTextureOffset(24, 7).addBox(-3.0F, -1.0F, 2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Head.setTextureOffset(26, 4).addBox(-1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		Head.setTextureOffset(24, 7).addBox(1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(4.0F, 1.0F, 1.0F);
		Head.addChild(Eyes);
		Eyes.setTextureOffset(56, 0).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Eyes.setTextureOffset(56, 0).addBox(-9.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(1.125F, -1.375F, -0.125F);
		Body.addChild(Legs);
		setRotationAngle(Legs, 0.0F, 0.0873F, 0.0F);
		Legs.setTextureOffset(35, 27).addBox(1.875F, -2.625F, -1.875F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		Legs.setTextureOffset(0, 0).addBox(-4.125F, -1.625F, -1.875F, 6.0F, 1.0F, 4.0F, 0.0F, false);

		legR = new ModelRenderer(this);
		legR.setRotationPoint(2.875F, -0.625F, -0.875F);
		Legs.addChild(legR);
		legR.setTextureOffset(47, 28).addBox(3.0F, -1.0F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		legR.setTextureOffset(47, 28).addBox(3.0F, -1.0F, -2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		legR.setTextureOffset(47, 28).addBox(3.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		legR.setTextureOffset(34, 21).addBox(2.0F, -2.0F, -2.0F, 2.0F, 1.0F, 5.0F, 0.0F, false);

		legL = new ModelRenderer(this);
		legL.setRotationPoint(-5.125F, -0.625F, 0.125F);
		Legs.addChild(legL);
		setRotationAngle(legL, 0.0F, 3.1416F, 0.0F);
		legL.setTextureOffset(47, 28).addBox(3.0F, -1.0F, -2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		legL.setTextureOffset(47, 28).addBox(3.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		legL.setTextureOffset(47, 28).addBox(3.0F, -1.0F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		legL.setTextureOffset(34, 21).addBox(2.0F, -2.0F, -2.0F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		legL.setTextureOffset(35, 27).addBox(-1.0F, -2.0F, -1.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
	}


	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}