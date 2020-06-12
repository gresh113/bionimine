package com.github.gresh113.bionimine.entities.husi;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public class HusiModel<T extends HusiEntity> extends EntityModel<T> {
	private final ModelRenderer Body;
	private final ModelRenderer Torso;
	private final ModelRenderer Neck;
	private final ModelRenderer Head;
	private final ModelRenderer LegL;
	private final ModelRenderer LowerLegR;
	private final ModelRenderer LegR;
	private final ModelRenderer LowerLegL;

	public HusiModel() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(-0.5F, 8.5F, 0.0F);
		setRotationAngle(Body, 0.0F, 1.5708F, 0.0F);
		

		

		LegR = new ModelRenderer(this);
		LegR.setRotationPoint(-3.5F, 15.5F, 3.0F);
		Body.addChild(LegR);
		LegR.setTextureOffset(0, 38).addBox(1.0F, -2.0F, -8.0F, 5.0F, 2.0F, 4.0F, 0.0F, false);
		LegR.setTextureOffset(18, 38).addBox(-1.0F, -4.0F, -7.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		LegR.setTextureOffset(8, 29).addBox(-2.0F, -14.0F, -7.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		LegL = new ModelRenderer(this);
		LegL.setRotationPoint(-3.5F, 15.5F, -5.0F);
		Body.addChild(LegL);
		LegL.setTextureOffset(8, 20).addBox(-2.0F, -14.0F, 8.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		LegL.setTextureOffset(0, 38).addBox(1.0F, -2.0F, 7.0F, 5.0F, 2.0F, 4.0F, 0.0F, false);
		LegL.setTextureOffset(18, 38).addBox(-1.0F, -4.0F, 8.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		
		LowerLegR = new ModelRenderer(this);
		LowerLegR.setRotationPoint(-4.5F, 9.5F, -4.0F);
		LegR.addChild(LowerLegR);
		setRotationAngle(LowerLegR, 0.0F, 0.0F, 2.618F);
		LowerLegR.setTextureOffset(0, 29).addBox(-1.634F, -5.634F, 0.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		LowerLegL = new ModelRenderer(this);
		LowerLegL.setRotationPoint(-4.5F, 9.5F, -4.0F);
		LowerLegR.addChild(LowerLegL);
		setRotationAngle(LowerLegL, 0.0F, 0.0F, 2.618F);
		LowerLegL.setTextureOffset(0, 20).addBox(-1.634F, -5.634F, 7.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(25.7071F, -11.8787F, 0.0F);
		Body.addChild(Head);
		setRotationAngle(Head, 0.0F, 0.0F, -2.3562F);
		Head.setTextureOffset(0, 0).addBox(7.3137F, -11.799F, -1.0F, 5.0F, 3.0F, 3.0F, 0.0F, false);
		Head.setTextureOffset(14, 0).addBox(1.3439F, -10.7581F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);

		Neck = new ModelRenderer(this);
		Neck.setRotationPoint(0.7071F, -0.8787F, 0.0F);
		Body.addChild(Neck);
		setRotationAngle(Neck, 0.0F, 0.0F, -2.3562F);
		Neck.setTextureOffset(0, 5).addBox(-0.5858F, 1.6569F, -1.0F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Torso);
		setRotationAngle(Torso, 0.0F, 0.0F, -2.3562F);
		Torso.setTextureOffset(12, 7).addBox(-1.0858F, -8.3284F, 1.0F, 5.0F, 11.0F, 2.0F, 0.0F, false);
		Torso.setTextureOffset(15, 6).addBox(-1.0858F, -8.3284F, -2.0F, 5.0F, 11.0F, 2.0F, 0.0F, false);
		Torso.setTextureOffset(29, 7).addBox(-3.0503F, -4.5355F, 0.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);
		Torso.setTextureOffset(30, 0).addBox(-1.5858F, -2.5858F, 0.0F, 6.0F, 6.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
	      this.LegR.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	      this.LegL.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}